package com.uy.ygsb.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.uy.ygsb.R;
import com.uy.ygsb.Activity.GameActivity;
import com.uy.ygsb.Data.Bags;
import com.uy.ygsb.Pojo.Item;
import com.uy.ygsb.Pojo.Response;
import com.uy.ygsb.Ui.GamePopup;
import com.uy.ygsb.Ui.GameToast;
import com.uy.ygsb.Ui.Dialog.GameDialog;
import com.uy.ygsb.View.ItemListAdapter;

public class GameBag implements OnClickListener {
	private Context context;
	private static GameBag gameBag;
	private List<Item> itemList = new ArrayList<Item>(),
			itemListCopy = new ArrayList<Item>();
	private GridView itemListView;
	private ItemListAdapter itemListAdapter;
	private GameDialog bagDialog;
	private Response response;
	private int bagId = 1;
	private RadioGroup bagGroup;
	private RadioButton bagAll, bagDurg, bagEquip, bagOther;
	private Item item; // 当前选中物品
	private GamePopup gamePopup;
	private Button btnUse, btnDrop, btnEquip, btnSell;

	public GameBag(final Context context) {
		this.context = context;

		this.bagDialog = new GameDialog(this.context);
		this.bagDialog.setTitle(R.string.text_bag);
		this.bagDialog.setOkText(R.string.btn_close);
		this.bagDialog.setCancelVisibility(View.GONE);
		this.bagDialog.setWidth(1.0);
		this.bagDialog.setContentView(R.layout.bag);
		this.bagDialog.show();

		this.gamePopup = new GamePopup(context);
		this.bagGroup = (RadioGroup) this.bagDialog
				.findViewById(R.id.bag_group);
		this.bagAll = (RadioButton) this.bagDialog.findViewById(R.id.bag_all);
		this.bagDurg = (RadioButton) this.bagDialog.findViewById(R.id.bag_drug);
		this.bagEquip = (RadioButton) this.bagDialog
				.findViewById(R.id.bag_equip);
		this.bagOther = (RadioButton) this.bagDialog
				.findViewById(R.id.bag_other);
		this.bagGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int id) {
				itemListCopy = cloneData();
				if (id == bagDurg.getId()) {
					itemListCopy = filterData(itemListCopy, 1);
				} else if (id == bagEquip.getId()) {
					itemListCopy = filterData(itemListCopy, 2);
				} else if (id == bagOther.getId()) {
					itemListCopy = filterData(itemListCopy, 3);
				}
				itemListAdapter.setItemList(itemListCopy);
				itemListAdapter.notifyDataSetChanged();
			}
		});
		this.itemListView = (GridView) bagDialog
				.findViewById(R.id.bag_item_list);
		this.itemListAdapter = new ItemListAdapter(this.context, this.itemList);
		this.itemListView.setAdapter(this.itemListAdapter);
		this.itemListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				item = itemListAdapter.getItemList().get(position);
				gamePopup.setMessage(
						Html.fromHtml("<font size='16' color='#FF6600'>"
								+ item.getName()
								+ "</font> <font color='#008000'>x"
								+ item.getNum() + "</font><br />"
								+ item.getDesc())).show(view);
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
						btnUse = (Button) gamePopup
								.findViewById(R.id.item_btn_use);
						btnUse.setOnClickListener(GameBag.this);
						if (item.getType() == 1) {
							btnUse.setVisibility(View.VISIBLE);
						}
						btnSell = (Button) gamePopup
								.findViewById(R.id.item_btn_sell);
						btnSell.setOnClickListener(GameBag.this);
						if (item.getSellAble()) {
							btnSell.setVisibility(View.VISIBLE);
						}
						btnDrop = (Button) gamePopup
								.findViewById(R.id.item_btn_drop);
						btnDrop.setOnClickListener(GameBag.this);
						if (item.getDropAble()) {
							btnDrop.setVisibility(View.VISIBLE);
						}
						btnEquip = (Button) gamePopup
								.findViewById(R.id.item_btn_equip);
						btnEquip.setOnClickListener(GameBag.this);
						return false;
					}

				});

		this.fetchData();
	}

	public static GameBag getInstance(Context context) {
		if (gameBag == null) {
			gameBag = new GameBag(context);
		}
		if (!gameBag.bagDialog.isShowing()) {
			gameBag.bagDialog.show();
		}
		return gameBag;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.item_btn_use:
			break;
		case R.id.item_btn_sell:
			new Thread(new Runnable() {
				public void run() {
					try {
						response = Bags.sell(((GameActivity) context).auth,
								bagId, item.getId(), item.getNum());
						mHandler.sendEmptyMessage(Constant.MSG_SUCCESS);
					} catch (Exception e) {
						mHandler.sendEmptyMessage(Constant.MSG_FAIL);
						e.printStackTrace();
					}
				}
			}).start();
			break;
		case R.id.item_btn_equip:
			break;
		case R.id.item_btn_drop:
			new Thread(new Runnable() {
				public void run() {
					try {
						response = Bags.drop(((GameActivity) context).auth,
								bagId, item.getId(), item.getNum());
						mHandler.sendEmptyMessage(Constant.MSG_SUCCESS);
					} catch (Exception e) {
						mHandler.sendEmptyMessage(Constant.MSG_FAIL);
						e.printStackTrace();
					}
				}
			}).start();
			break;
		}
		gamePopup.dismiss();
	}

	// 过滤数据
	private List<Item> filterData(List<Item> itemListSrc, int cateId) {
		Iterator<Item> it = itemListSrc.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getCategory() != cateId) {
				it.remove();
				itemListSrc.remove(item);
			}
		}
		return itemListSrc;
	}

	// 删除数据
	private List<Item> remove(List<Item> itemListSrc, int id) {
		Iterator<Item> it = itemListSrc.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getId() == id) {
				it.remove();
				itemListSrc.remove(item);
			}
		}
		return itemListSrc;
	}

	// 克隆数据
	private List<Item> cloneData() {
		List<Item> itemListCopy = new ArrayList<Item>();
		for (Item item : itemList) {
			itemListCopy.add(item);
		}
		return itemListCopy;
	}

	// 处理返回数据
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constant.MSG_FAIL:
				((GameActivity) context).showErr();
				break;
			case Constant.MSG_SUCCESS:
				GameToast.makeText(context, response.getMsg(),
						GameToast.LENGTH_SHORT).show();
				if (response.getCode() == 1) {
					itemListAdapter.setItemList(remove(itemListCopy,
							item.getId()));
					itemListAdapter.notifyDataSetChanged();
				}
				break;
			case Constant.MSG_BAG_GET_SUCCESS:
				if (response.getCode() == 1) {
					itemList = (List<Item>) response.getData();
					itemListCopy = cloneData();
					if (itemList.size() > 0) {
						itemListAdapter.setItemList(itemList);
						itemListAdapter.notifyDataSetChanged();
					}
				} else {
					GameToast.makeText(context, response.getMsg(),
							GameToast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};

	public void fetchData() {
		// 请求背包数据
		new Thread(new Runnable() {
			public void run() {
				try {
					response = Bags
							.getBag(((GameActivity) context).auth, bagId);
					mHandler.sendEmptyMessage(Constant.MSG_BAG_GET_SUCCESS);
				} catch (Exception e) {
					mHandler.sendEmptyMessage(Constant.MSG_FAIL);
					e.printStackTrace();
				}
			}
		}).start();
	}

}