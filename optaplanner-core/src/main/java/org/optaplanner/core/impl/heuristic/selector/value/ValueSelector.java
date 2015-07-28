/*
 * Copyright 2012 JBoss Inc
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

package org.optaplanner.core.impl.heuristic.selector.value;

import java.util.Iterator;

import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.impl.domain.variable.descriptor.GenuineVariableDescriptor;
import org.optaplanner.core.impl.heuristic.selector.IterableSelector;
import org.optaplanner.core.impl.heuristic.selector.Selector;

/**
 * Selects values from the {@link ValueRangeProvider} for a {@link PlanningVariable} annotated property.
 * @see AbstractValueSelector
 */
public interface ValueSelector extends Selector {

    /**
     * @return never null
     */
    GenuineVariableDescriptor getVariableDescriptor();

    /**
     * Similar to {@link IterableSelector#getSize()}, but requires an entity.
     * @param entity never null
     * @return the approximate number of elements generated by this {@link Selector}, always {@code >= 0}
     * @throws IllegalStateException if {@link #isCountable} returns false,
     * but not if only {@link #isNeverEnding()} returns true
     */
    long getSize(Object entity);

    /**
     * Similar to {@link IterableSelector#iterator()}, but requires an entity.
     * @param entity never null
     * @return never null
     */
    Iterator<Object> iterator(Object entity);

    /**
     * If {@link #isNeverEnding()} is true, then {@link #iterator(Object)} will never end.
     * This returns an ending {@link Iterator}, that tries to match {@link #iterator(Object)} as much as possible,
     * but return each distinct element only once
     * and therefore it might not respect the configuration of this {@link ValueSelector} entirely.
     * @param entity never null
     * @return never null
     * @see #iterator(Object)
     */
    Iterator<Object> endingIterator(Object entity);

}
