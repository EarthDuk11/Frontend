// Generated by view binder compiler. Do not edit!
package com.example.team11.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.team11.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDiaryDetailBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final FloatingActionButton button1;

  @NonNull
  public final FloatingActionButton button2;

  @NonNull
  public final FloatingActionButton button3;

  @NonNull
  public final TextView textViewTaxiOrWalk;

  @NonNull
  public final TextView toolImg;

  @NonNull
  public final Toolbar toolbar;

  private FragmentDiaryDetailBinding(@NonNull LinearLayout rootView,
      @NonNull FloatingActionButton button1, @NonNull FloatingActionButton button2,
      @NonNull FloatingActionButton button3, @NonNull TextView textViewTaxiOrWalk,
      @NonNull TextView toolImg, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.button1 = button1;
    this.button2 = button2;
    this.button3 = button3;
    this.textViewTaxiOrWalk = textViewTaxiOrWalk;
    this.toolImg = toolImg;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDiaryDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDiaryDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_diary_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDiaryDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button1;
      FloatingActionButton button1 = ViewBindings.findChildViewById(rootView, id);
      if (button1 == null) {
        break missingId;
      }

      id = R.id.button2;
      FloatingActionButton button2 = ViewBindings.findChildViewById(rootView, id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.button3;
      FloatingActionButton button3 = ViewBindings.findChildViewById(rootView, id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.textViewTaxiOrWalk;
      TextView textViewTaxiOrWalk = ViewBindings.findChildViewById(rootView, id);
      if (textViewTaxiOrWalk == null) {
        break missingId;
      }

      id = R.id.tool_img;
      TextView toolImg = ViewBindings.findChildViewById(rootView, id);
      if (toolImg == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new FragmentDiaryDetailBinding((LinearLayout) rootView, button1, button2, button3,
          textViewTaxiOrWalk, toolImg, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}