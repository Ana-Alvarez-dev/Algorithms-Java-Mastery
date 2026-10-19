package org.anaalvarezdev.algorithms.stacksandqueues;

class LinkedStackTest extends StackContractTest {
    @Override protected <E> Stack<E> create() { return new LinkedStack<>(); }
    @Override protected boolean valid(Stack<?> stack) { return ((LinkedStack<?>) stack).hasValidStructure(); }
}
