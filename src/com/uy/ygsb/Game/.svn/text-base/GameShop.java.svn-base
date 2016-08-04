package com.uy.ygsb.Game;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.uy.ygsb.R;
import com.uy.ygsb.Activity.GameActivity;
import com.uy.ygsb.Data.Shops;
import com.uy.ygsb.Pojo.Item;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Ui.GamePopup;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Ui.Dialog.GameBaseDialog;
import com.uy.ygsb.View.ShopListAdapter;

public class GameShop implements OnClickListener {
	private Context context;
	private static GameShop gameShop;
	private List<Item> itemList = new ArrayList<Item>(),
			hotList = new ArrayList<Item>(), porpList = new ArrayList<Item>(),
			diamondList = new ArrayList<Item>();
	private GridView itemListView;
	private ShopListAdapter itemListAdapter;
	private GameBaseDialog shopDialog;
	private Response response;
	private int shopId = 1;
	private RadioGroup shopTab;
	private RadioButton shopHot, shopProp, shopDiamond;
	private ImageButton btnClose;
	private GamePopup gamePopup;
	private Button btnBuy;
	private Item item; // 当前选中物品

	public GameShop(final Context context) {
		this.context = context;
		this.shopDialog = new GameBaseDialog(this.context);
		this.shopDialog.setContentView(R.layout.shop);
		this.shopDialog.show();

		this.shopTab = (RadioGroup) this.shopDialog.findViewById(R.id.shop_tab);
		this.shopHot = (RadioButton) this.shopDialog
				.findViewById(R.id.shop_tab_hot);
		this.shopProp = (RadioButton) this.shopDialog
				.findViewById(R.id.shop_tab_prop);
		this.shopDiamond = (RadioButton) this.shopDialog
				.findViewById(R.id.shop_tab_diamond);

		this.btnClose = (ImageButton) this.shopDialog
				.findViewById(R.id.btn_shop_close);
		this.btnClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				shopDialog.dismiss();
			}
		});

		this.shopTab.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int id) {
				if (id == shopProp.getId()) {
					if (porpList.size() <= 0) {
						shopId = 2;
						fetchData();
					} else {
						itemListAdapter.setItemList(porpList);
					}
				} else if (id == shopDiamond.getId()) {
					if (diamondList.size() <= 0) {
						shopId = 3;
						fetchData();
					} else {
						itemListAdapter.setItemList(diamondList);
					}
				} else {
					if (hotList.size() <= 0) {
						shopId = 1;
						fetchData();
					} else {
						itemListAdapter.setItemList(hotList);
					}
				}
				itemListAdapter.notifyDataSetChanged();
			}
		});

		this.gamePopup = new GamePopup(context);

		this.itemListView = (GridView) shopDialog.findViewById(R.id.shop_list);
		this.itemListAdapter = new ShopListAdapter(this.context, this.itemList);
		this.itemListView.setAdapter(this.itemListAdapter);
		this.itemListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				item = itemListAdapter.getItemList().get(position);
				gamePopup.setMessage(item.getDesc()).show(view);
			}
		});
		this.itemListView
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						item = itemListAdapter.getItemList().get(position);
						gamePopup.setContentView(R.layout.item_popup_action)
								.show(view);
						btnBuy = (Button) gamePopup
								.findViewById(R.id.item_btn_buy);
						btnBuy.setVisibility(View.VISIBLE);
						btnBuy.setOnClickListener(GameShop.this);
						return false;
					}

				});
		this.fetchData();
	}

	public static GameShop getInstance(Context context) {
		if (gameShop == null) {
			gameShop = new GameShop(context);
		}
		if (!gameShop.shopDialog.isShowing()) {
			gameShop.shopDialog.show();
		}
		return gameShop;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.item_btn_buy:
			gamePopup.dismiss();
			new Thread(new Runnable() {
				public void run() {
					try {
						response = Shops.buy(((GameActivity) context).auth,
								item.getTypeId(), 1);
						mHandler.sendEmptyMessage(Constant.MSG_SHOP_BUY_SUCCESS);
					} catch (Exception e) {
						mHandler.sendEmptyMessage(Constant.MSG_FAIL);
						e.printStackTrace();
					}
				}
			}).start();
			break;
		}
	}

	// 处理返回数据
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_FAIL:
				((GameActivity) context).showErr();
				break;
			case Constant.MSG_SUCCESS:
				if (response.getCode() == 1) {
					itemList = (List<Item>) response.getData();
					if (shopId == 1) {
						hotList = itemList;
					} else if (shopId == 2) {
						porpList = itemList;
					} else {
						diamondList = itemList;
					}
					itemListAdapter.setItemList(itemList);
					itemListAdapter.notifyDataSetChanged();
				} else {
					GameToast.makeText(context, response.getMsg(),
							GameToast.LENGTH_SHORT).show();
				}
				break;
			case Constant.MSG_SHOP_BUY_SUCCESS:
				GameToast.makeText(context, response.getMsg(),
						GameToast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	public void fetchData() {
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Shops.getShop(((GameActivity) context).auth,
							shopId);
					mHandler.sendEmptyMessage(Constant.MSG_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

}