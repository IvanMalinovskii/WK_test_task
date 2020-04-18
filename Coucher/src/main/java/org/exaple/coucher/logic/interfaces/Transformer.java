package org.exaple.coucher.logic.interfaces;

/**
 * transforms an input XML string
 */
public interface Transformer {
    /**
     * does transformation
     * @param initialXML input XML string to transform
     * @return returns a transformed string
     */
    String getTransformed(String initialXML);
}
