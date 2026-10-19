package org.anaalvarezdev.algorithms.hashtables;

import java.util.function.Consumer;

final class SmallArrays {
    private SmallArrays() { }
    static void forEach(Consumer<int[]> check) {
        for(int length=0;length<=6;length++) {
            for(int code=0;code<(int)Math.pow(3,length);code++) {
                int[] values=new int[length];int remaining=code;
                for(int index=0;index<length;index++) { values[index]=remaining%3-1;remaining/=3; }
                check.accept(values);
            }
        }
    }
}
