//package com.java.plus.reactor.rxjava;
// 
//import java.util.List;
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.ObservableOnSubscribe; 
//import io.reactivex.annotations.NonNull;
//import io.reactivex.functions.Consumer;
//import io.reactivex.schedulers.Schedulers;
// 
//
//public class RxDemo {
//    public Observable<String> search(String name) {
//        return Observable.unsafeCreate(new OnSubscribeSearch<>(name));
//    }
//    
//    public void test() {
//    	List list= search("").toList().blockingGet();
//    }
//}
