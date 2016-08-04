package com.uy.ygsb.View;

import java.util.List;

import com.uy.ygsb.R;
import com.uy.ygsb.Pojo.Role;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RoleListAdapter extends BaseAdapter {

	private Context context;
	private List<Role> roleList;

	public RoleListAdapter(Context context, List<Role> roleList) {
		this.context = context;
		this.roleList = roleList;
	}

	@Override
	public int getCount() {
		return this.roleList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.roleList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		Role role = roleList.get(position);
		if (view == null) {
			view = LayoutInflater.from(this.context).inflate(
					R.layout.select_role_item, null);
		}
		TextView roleName = (TextView) view.findViewById(R.id.role_name);
		TextView roleLevel = (TextView) view.findViewById(R.id.role_level);
		TextView roleEnergy = (TextView) view.findViewById(R.id.role_energy);
		TextView roleCamp = (TextView) view.findViewById(R.id.role_camp);
		TextView roleJob = (TextView) view.findViewById(R.id.role_job);
		roleName.setText(role.getName());
		roleLevel.setText("Lv " + role.getLevel());
		roleEnergy.setText(role.getEnergy() + "");
		roleCamp.setText(role.getCampName());
		roleJob.setText(role.getJobName());
		return view;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
}
