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

/**
 * Chain of responsibility pattern.
 * <p>
 * But it's a special chain, because
 * 1. {@link GrammarMultiChains} can set one or more next chains.
 * 2. {@link MultiGrammarsChain} can add one or more grammars.
 * 3. It's very particular for {@link GrammarDoElseChain}. First, {@link GrammarDoElseChain} can set one or more next chains.
 * Second, if chain can handle it, then, let other chains to handle it too.
 * 4. {@link GrammarSingleChain} is real chain of responsibility pattern.
 * <p>
 * Created by yuyidong on 16/5/4.
 */
public interface IChain {
    /**
     * Whether the grammar in this chain can handle it
     *
     * @param charSequence the content
     * @return TRUE: can handle it
     */
    @NonNull
    boolean handleGrammar(@NonNull CharSequence charSequence);

    /**
     * If this chain can't handle it, let others handle it.
     * This method can add one or more next chains, more see {@link GrammarMultiChains} and {@link GrammarDoElseChain}.
     *
     * @param nextHandleGrammar the next chain
     * @return Only return TRUE in {@link GrammarMultiChains}
     */
    boolean addNextHandleGrammar(@NonNull IChain nextHandleGrammar);

    /**
     * Set next chain.
     * If this chain can't handle it, let the next chain handle it.
     *
     * @param nextHandleGrammar the next chain
     * @return Only return FALSE in {@link GrammarMultiChains}
     */
    boolean setNextHandleGrammar(@NonNull IChain nextHandleGrammar);

}
