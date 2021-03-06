/**
 * ---------------------------------------------------------------------
 * GLPI Android Inventory Agent
 * Copyright (C) 2019 Teclib.
 *
 * https://glpi-project.org
 *
 * Based on Flyve MDM Inventory Agent For Android
 * Copyright © 2018 Teclib. All rights reserved.
 *
 * ---------------------------------------------------------------------
 *
 *  LICENSE
 *
 *  This file is part of GLPI Android Inventory Agent.
 *
 *  GLPI Android Inventory Agent is a subproject of GLPI.
 *
 *  GLPI Android Inventory Agent is free software: you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 3
 *  of the License, or (at your option) any later version.
 *
 *  GLPI Android Inventory Agent is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  ---------------------------------------------------------------------
 *  @copyright Copyright © 2019 Teclib. All rights reserved.
 *  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 *  @link      https://github.com/glpi-project/android-inventory-agent
 *  @link      https://glpi-project.org/glpi-network/
 *  ---------------------------------------------------------------------
 */

package org.glpi.inventory.agent.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.glpi.inventory.agent.R;
import org.glpi.inventory.agent.schema.ListInventorySchema;
import org.glpi.inventory.agent.utils.Helpers;

import java.util.ArrayList;


public class InventoryChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ListInventorySchema> data;
    private FragmentActivity activity;

    InventoryChildAdapter(ArrayList<ListInventorySchema> data, FragmentActivity activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int resource = R.layout.list_item_inventory_child;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DataViewHolder) holder).bindData(data.get(position), position);
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        View viewSeparatorBottom;

        DataViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            viewSeparatorBottom = itemView.findViewById(R.id.viewSeparatorBottom);
        }

        void bindData(ListInventorySchema model, int position) {
            if (position % 2 == 1) {
                itemView.setBackgroundColor(activity.getResources().getColor(R.color.white));
            } else {
                itemView.setBackgroundColor(activity.getResources().getColor(R.color.grayDarkList));
            }

            title.setText(Html.fromHtml(Helpers.splitCamelCase(model.getTitle())));
            description.setText(Html.fromHtml(model.getDescription()));

            if ((data.size() -1) == position) {
                viewSeparatorBottom.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}