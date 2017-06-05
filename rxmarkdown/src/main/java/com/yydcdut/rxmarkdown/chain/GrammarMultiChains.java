/*
 * Copyright (C) 2016 yydcdut (yuyidong2015@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.yydcdut.rxmarkdown.chain;

import android.support.annotation.NonNull;

import com.yydcdut.rxmarkdown.syntax.IGrammar;

import java.util.ArrayList;
import java.util.List;

/**
 * This Chain can set one or more next chains.
 * <p>
 * Created by yuyidong on 16/5/4.
 */
public class GrammarMultiChains implements IChain {
    private IGrammar mGrammar;

    private List<IChain> mNextHandleGrammarList = null;

    /**
     * Constructor
     *
     * @param grammar the grammar
     */
    public GrammarMultiChains(@NonNull IGrammar grammar) {
        mGrammar = grammar;
    }

    @NonNull
    @Override
    public boolean handleGrammar(@NonNull CharSequence charSequence) {
        if (mGrammar.isMatch(charSequence)) {
            mGrammar.format(charSequence);
        }
        if (mNextHandleGrammarList != null) {
            boolean handled = false;
            for (IChain responsibilityChain : mNextHandleGrammarList) {
                handled |= responsibilityChain.handleGrammar(charSequence);
            }
            return handled;
        } else {
            return false;
        }
    }

    @Override
    public boolean addNextHandleGrammar(@NonNull IChain nextHandleGrammar) {
        if (mNextHandleGrammarList == null) {
            mNextHandleGrammarList = new ArrayList<>();
        }
        mNextHandleGrammarList.add(nextHandleGrammar);
        return true;
    }

    /**
     * @param nextHandleGrammar the next chain
     * @return boolean
     * @deprecated
     */
    @Override
    @Deprecated
    public boolean setNextHandleGrammar(@NonNull IChain nextHandleGrammar) {
        return false;
    }

    @Override
    public String toString() {
        return "GrammarMultiChains{" +
                "mGrammar=" + mGrammar +
                ", mNextHandleGrammarList=" + mNextHandleGrammarList +
                '}';
    }
}
