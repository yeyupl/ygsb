package com.uy.ygsb.View;

import java.util.List;

import com.uy.ygsb.R;
import com.uy.ygsb.Pojo.Server;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ServerListAdapter extends BaseAdapter {

	private Context context;
	private List<Server> serverList;

	public ServerListAdapter(Context context, List<Server> serverList) {
		this.context = context;
		this.serverList = serverList;
	}

	@Override
	public int getCount() {
		return this.serverList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.serverList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		Server server = serverList.get(position);
		if (view == null) {
			view = LayoutInflater.from(this.context).inflate(
					R.layout.select_server_item, null);
		}
		TextView serverName = (TextView) view.findViewById(R.id.server_name);
		serverName.setText(server.getName() + "(" + server.getUnum() + ")");
		switch (server.getStatus()) {
		case 1:
			serverName.setTextColor(context.getResources().getColor(
					R.color.gray));
			break;
		case 2:
			serverName.setTextColor(context.getResources().getColor(
					R.color.green));
			break;
		case 3:
			serverName.setTextColor(context.getResources().getColor(
					R.color.orange));
			break;
		case 4:
			serverName.setTextColor(context.getResources()
					.getColor(R.color.red));
			break;
		}
		return view;
	}

	public void setServerList(List<Server> serverList) {
		this.serverList = serverList;
	}

	public List<Server> getServerList() {
		return this.serverList;
	}
}
