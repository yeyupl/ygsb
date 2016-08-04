package com.uy.ygsb.View;

import java.util.List;

import com.uy.ygsb.R;
import com.uy.ygsb.Pojo.Item;
import com.uy.ygsb.Util.ImageUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopListAdapter extends BaseAdapter {

	private Context context;
	private List<Item> itemList;

	public ShopListAdapter(Context context, List<Item> itemList) {
		this.context = context;
		this.itemList = itemList;
	}

	@Override
	public int getCount() {
		return this.itemList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.itemList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		Item item = itemList.get(position);
		if (view == null) {
			view = LayoutInflater.from(this.context).inflate(
					R.layout.shop_item, null);
		}
		ImageView itemImg = (ImageView) view.findViewById(R.id.shop_item_img);
		TextView itemName = (TextView) view.findViewById(R.id.shop_item_name);
		TextView itemPrice = (TextView) view.findViewById(R.id.shop_item_price);

		itemImg.setImageBitmap(ImageUtil.getInstance(context).getImage(
				item.getSkin()));
		itemName.setText(item.getName());
		itemPrice.setText(item.getBuyGold() + "");

		return view;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Item> getItemList() {
		return this.itemList;
	}
}
