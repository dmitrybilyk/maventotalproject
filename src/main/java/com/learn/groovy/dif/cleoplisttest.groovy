package com.learn.groovy.dif

class cleoplisttest {
    public static void main(String[] args) {

    }

    void getSetDesiredDateForAllTxsIfItIsSet() {
        def recordList = batch.record
        def desiredDate = batch.record[0].requestCollectionDate
        if(desiredDate){
//            for (Record record  in recordList){
//                record.requestCollectionDate = desiredDate
//            }
        }
    }
}
