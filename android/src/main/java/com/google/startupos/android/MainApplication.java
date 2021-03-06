/*
 * Copyright 2018 The StartupOS Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.startupos.android;

import android.app.Application;

import java.io.IOException;

import com.google.firebase.FirebaseApp;
import android.util.Log;
import org.json.JSONException;

import com.google.startupos.android.FirestoreInitializer;

public class MainApplication extends Application {

  public static final String TAG = MainApplication.class.getSimpleName();

  @Override
  public void onCreate() {
    super.onCreate();
    try {
      FirestoreInitializer.init(this);
    } catch (IOException e) {
      Log.e(TAG, "Firebase init failed: google-services.json not found");
    } catch (JSONException e) {
      Log.e(TAG, "Firebase init failed: google-services.json has incorrect structure");
    }
  }
}

