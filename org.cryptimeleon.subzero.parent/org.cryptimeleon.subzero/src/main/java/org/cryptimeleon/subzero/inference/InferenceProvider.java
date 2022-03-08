package org.cryptimeleon.subzero.inference;

import org.cryptimeleon.subzero.model.AugmentedModel;

import java.util.Map;

public interface InferenceProvider<T, U> {

    Map<T, U> infer(AugmentedModel augmentedModel);

}
