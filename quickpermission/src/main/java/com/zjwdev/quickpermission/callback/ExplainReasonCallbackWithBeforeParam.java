/*
 * Copyright (C)  Jackie-ZJW, QuickPermission Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zjwdev.quickpermission.callback;

import androidx.annotation.NonNull;

import com.zjwdev.quickpermission.request.ExplainScope;
import com.zjwdev.quickpermission.request.PermissionBuilder;

import java.util.List;

/**
 * Callback for {@link PermissionBuilder#onExplainRequestReason(ExplainReasonCallbackWithBeforeParam)} method.
 *
 * @author Jackie-ZJW
 * @since 2020/6/7
 */
public interface ExplainReasonCallbackWithBeforeParam {
    
    /**
     * Called when you should explain why you need these permissions.
     *
     * @param scope         Scope to show rationale dialog.
     * @param deniedList    Permissions that you should explain.
     * @param beforeRequest Indicate it's before or after permission request. Work with {@link PermissionBuilder#explainReasonBeforeRequest()}
     */
    void onExplainReason(@NonNull ExplainScope scope, @NonNull List<String> deniedList, boolean beforeRequest);
    
}
