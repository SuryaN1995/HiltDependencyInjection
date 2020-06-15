package com.airtel.hiltapp

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by SURYA N on 4/6/20.
 */
class RxSchedulerRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                val immediate = object : Scheduler() {
                    override fun scheduleDirect(
                        run: Runnable,
                        delay: Long,
                        unit: TimeUnit
                    ): Disposable {
                        return super.scheduleDirect(run, 0, unit)
                    }

                    override fun createWorker(): Worker {
                        return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                    }
                }
                RxJavaPlugins.setInitIoSchedulerHandler { immediate }
                RxJavaPlugins.setComputationSchedulerHandler { immediate }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
                RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
                RxAndroidPlugins.setMainThreadSchedulerHandler { immediate }

                try {
                    base?.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }

        }

    }
}