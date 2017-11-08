/*
 * Copyright 2017 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.gchq.koryphe.impl.function;

import com.google.common.collect.Iterables;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import uk.gov.gchq.koryphe.function.KorypheFunction;

import java.util.stream.Stream;

/**
 * A {@code LastItem} is a {@link KorypheFunction} that returns the last item from a provided
 * {@link Iterable} of items of type T
 *
 * @param <T> the type of objects in the iterable
 */
public class LastItem<T> extends KorypheFunction<Iterable<T>, Stream<T>> {

    @Override
    @SuppressFBWarnings(value = "DE_MIGHT_IGNORE", justification = "Any exceptions are to be ignored")
    public Stream<T> apply(final Iterable<T> input) {
        if (null == input) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        try {
            return Stream.of(Iterables.getLast(input, null));
        } finally {
            if (input instanceof AutoCloseable) {
                try {
                    ((AutoCloseable) input).close();
                } catch (final Exception e) {
                    // Ignore exception
                }
            }
        }
    }
}
