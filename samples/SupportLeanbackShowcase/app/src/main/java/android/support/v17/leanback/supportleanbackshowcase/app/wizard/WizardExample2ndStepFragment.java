/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package android.support.v17.leanback.supportleanbackshowcase.app.wizard;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.GuidedStepFragment;
import android.support.v17.leanback.supportleanbackshowcase.R;
import android.support.v17.leanback.widget.GuidanceStylist;
import android.support.v17.leanback.widget.GuidedAction;
import android.support.v17.leanback.widget.GuidedActionsStylist;
import android.widget.Toast;

import java.util.List;

/**
 * Displays the second screen of the rental wizard which requires the user to confirm his purchase.
 */
public class WizardExample2ndStepFragment extends WizardExampleBaseStepFragment {

    private static final String ARG_HD = "hd";
    private static final int ACTION_ID_CONFIRM = 1;
    private static final int ACTION_ID_PAYMENT_METHOD = ACTION_ID_CONFIRM + 1;

    public static GuidedStepFragment build(boolean hd, WizardExampleBaseStepFragment previousFragment) {
        GuidedStepFragment fragment = new WizardExample2ndStepFragment();
        // Reuse the same arguments this fragment was given.
        Bundle args = previousFragment.getArguments();
        args.putBoolean(ARG_HD, hd);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWizardActivity().setStep(2);
    }

    @NonNull
    @Override
    public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState) {
        GuidanceStylist.Guidance guidance = new GuidanceStylist.Guidance(mMovie.getTitle(),
                getString(R.string.wizard_example_rental_period),
                mMovie.getBreadcrump(), null);
        return guidance;

    }

    @Override
    public void onCreateActions(@NonNull List<GuidedAction> actions, Bundle savedInstanceState) {
        boolean rentHighDefinition = getArguments().getBoolean(ARG_HD);

        GuidedAction action = new GuidedAction.Builder()
                .id(ACTION_ID_CONFIRM)
                .title(getString(R.string.wizard_example_rent))
                .description(rentHighDefinition ? mMovie.getPriceHd() : mMovie.getPriceSd())
                .editable(false)
                .build();
        actions.add(action);
        action = new GuidedAction.Builder()
                .id(ACTION_ID_PAYMENT_METHOD)
                .title(getString(R.string.wizard_example_payment_method))
                .description(getString(R.string.wizard_example_visa_balance))
                .editable(false)
                .build();
        actions.add(action);
    }

    @Override
    public void onGuidedActionClicked(GuidedAction action) {
        if (ACTION_ID_PAYMENT_METHOD == action.getId()) {
            Toast.makeText(getActivity(),
                    getString(R.string.wizard_example_toast_payment_method_clicked),
                    Toast.LENGTH_SHORT).show();
        } else {
            GuidedStepFragment fragment = new WizardExample3rdStepFragment();
            fragment.setArguments(getArguments());
            add(getFragmentManager(), fragment);
        }
    }
}