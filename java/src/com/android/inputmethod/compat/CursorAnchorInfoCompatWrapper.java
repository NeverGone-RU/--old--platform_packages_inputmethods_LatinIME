/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.inputmethod.compat;

import android.graphics.Matrix;
import android.graphics.RectF;

import com.android.inputmethod.annotations.UsedForTesting;

@UsedForTesting
public final class CursorAnchorInfoCompatWrapper {

    /**
     * The insertion marker or character bounds have at least one visible region.
     */
    public static final int FLAG_HAS_VISIBLE_REGION = 0x01;

    /**
     * The insertion marker or character bounds have at least one invisible (clipped) region.
     */
    public static final int FLAG_HAS_INVISIBLE_REGION = 0x02;

    // Note that CursorAnchorInfo has been introduced in API level XX (Build.VERSION_CODE.LXX).
    private static final CompatUtils.ClassWrapper sCursorAnchorInfoClass;
    private static final CompatUtils.ToObjectMethodWrapper<RectF> sGetCharacterRectMethod;
    private static final CompatUtils.ToIntMethodWrapper sGetCharacterRectFlagsMethod;
    private static final CompatUtils.ToObjectMethodWrapper<CharSequence> sGetComposingTextMethod;
    private static final CompatUtils.ToIntMethodWrapper sGetComposingTextStartMethod;
    private static final CompatUtils.ToFloatMethodWrapper sGetInsertionMarkerBaselineMethod;
    private static final CompatUtils.ToFloatMethodWrapper sGetInsertionMarkerBottomMethod;
    private static final CompatUtils.ToFloatMethodWrapper sGetInsertionMarkerHorizontalMethod;
    private static final CompatUtils.ToFloatMethodWrapper sGetInsertionMarkerTopMethod;
    private static final CompatUtils.ToObjectMethodWrapper<Matrix> sGetMatrixMethod;
    private static final CompatUtils.ToIntMethodWrapper sGetInsertionMarkerFlagsMethod;

    private static int COMPOSING_TEXT_START_DEFAULT = -1;
    static {
        sCursorAnchorInfoClass = CompatUtils.getClassWrapper(
                "android.view.inputmethod.CursorAnchorInfo");
        sGetCharacterRectMethod = sCursorAnchorInfoClass.getMethod(
                "getCharacterRect", (RectF)null, int.class);
        sGetCharacterRectFlagsMethod = sCursorAnchorInfoClass.getPrimitiveMethod(
                "getCharacterRectFlags", 0, int.class);
        sGetComposingTextMethod = sCursorAnchorInfoClass.getMethod(
                "getComposingText", (CharSequence)null);
        sGetComposingTextStartMethod = sCursorAnchorInfoClass.getPrimitiveMethod(
                "getComposingTextStart", COMPOSING_TEXT_START_DEFAULT);
        sGetInsertionMarkerBaselineMethod = sCursorAnchorInfoClass.getPrimitiveMethod(
                "getInsertionMarkerBaseline", 0.0f);
        sGetInsertionMarkerBottomMethod = sCursorAnchorInfoClass.getPrimitiveMethod(
                "getInsertionMarkerBottom", 0.0f);
        sGetInsertionMarkerHorizontalMethod = sCursorAnchorInfoClass.getPrimitiveMethod(
                "getInsertionMarkerHorizontal", 0.0f);
        sGetInsertionMarkerTopMethod = sCursorAnchorInfoClass.getPrimitiveMethod(
                "getInsertionMarkerTop", 0.0f);
        sGetMatrixMethod = sCursorAnchorInfoClass.getMethod("getMatrix", (Matrix)null);
        sGetInsertionMarkerFlagsMethod = sCursorAnchorInfoClass.getPrimitiveMethod(
                "getInsertionMarkerFlags", 0);
    }

    @UsedForTesting
    public static boolean isAvailable() {
        return sCursorAnchorInfoClass.exists();
    }

    private Object mInstance;

    private CursorAnchorInfoCompatWrapper(final Object instance) {
        mInstance = instance;
    }

    @UsedForTesting
    public static CursorAnchorInfoCompatWrapper fromObject(final Object instance) {
        if (!isAvailable()) {
            return new CursorAnchorInfoCompatWrapper(null);
        }
        return new CursorAnchorInfoCompatWrapper(instance);
    }

    private static final class FakeHolder {
        static CursorAnchorInfoCompatWrapper sInstance = new CursorAnchorInfoCompatWrapper(null);
    }

    @UsedForTesting
    public static CursorAnchorInfoCompatWrapper getFake() {
        return FakeHolder.sInstance;
    }

    public CharSequence getComposingText() {
        return sGetComposingTextMethod.invoke(mInstance);
    }

    public int getComposingTextStart() {
        return sGetComposingTextStartMethod.invoke(mInstance);
    }

    public Matrix getMatrix() {
        return sGetMatrixMethod.invoke(mInstance);
    }

    public RectF getCharacterRect(final int index) {
        return sGetCharacterRectMethod.invoke(mInstance, index);
    }

    public int getCharacterRectFlags(final int index) {
        return sGetCharacterRectFlagsMethod.invoke(mInstance, index);
    }

    public float getInsertionMarkerBaseline() {
        return sGetInsertionMarkerBaselineMethod.invoke(mInstance);
    }

    public float getInsertionMarkerBottom() {
        return sGetInsertionMarkerBottomMethod.invoke(mInstance);
    }

    public float getInsertionMarkerHorizontal() {
        return sGetInsertionMarkerHorizontalMethod.invoke(mInstance);
    }

    public float getInsertionMarkerTop() {
        return sGetInsertionMarkerTopMethod.invoke(mInstance);
    }

    public int getInsertionMarkerFlags() {
        return sGetInsertionMarkerFlagsMethod.invoke(mInstance);
    }
}