package com.asuka.androidopensourcelibrarystudydemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.asuka.androidopensourcelibrarystudydemo.R;
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityRxjavaBinding;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class RxjavaActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;
    ActivityRxjavaBinding binding;
    public FlowableEmitter<Integer> emitter;
    private int a =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRxjavaBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_rxjava);
        setContentView(binding.getRoot());
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add( Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                emitter = e;
            }
        }, BackpressureStrategy.MISSING)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Timber.d(""+integer);
            }
        }));


        binding.FlowableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 140; i++) {
                    emitter.onNext(i);
                }
            }
        });
    }
}