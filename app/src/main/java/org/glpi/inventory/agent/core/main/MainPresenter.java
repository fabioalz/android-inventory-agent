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

package org.glpi.inventory.agent.core.main;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPresenter implements Main.Presenter {

    private Main.View view;
    private Main.Model model;

    public MainPresenter(Main.View view){
        this.view = view;
        model = new MainModel(this);
    }

    @Override
    public void showError(String message) {
        if(view!=null) {
            view.showError(message);
        }
    }

    @Override
    public Map<String, String> setupDrawer(Activity activity, ListView lst) {
        return model.setupDrawer(activity, lst);
    }

    @Override
    public void loadFragment(FragmentManager fragmentManager, Toolbar toolbar, Map<String, String> item) {
        model.loadFragment(fragmentManager, toolbar, item);
    }

    @Override
    public List<HashMap<String, String>> getMenuItem() {
        return model.getMenuItem();
    }

    @Override
    public void setupInventoryAlarm(Context context) {
        model.setupInventoryAlarm(context);
    }
}
