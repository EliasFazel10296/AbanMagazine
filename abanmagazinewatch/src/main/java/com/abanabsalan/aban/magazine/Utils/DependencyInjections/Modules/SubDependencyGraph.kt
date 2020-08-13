/*
 * Copyright © 2020 By Geeks Empire.
 *
 * Created by Elias Fazel on 8/12/20 11:59 PM
 * Last modified 6/28/20 2:44 PM
 *
 * Licensed Under MIT License.
 * https://opensource.org/licenses/MIT
 */

package com.abanabsalan.aban.magazine.Utils.DependencyInjections.Modules

import com.abanabsalan.aban.magazine.Utils.DependencyInjections.SubComponents.NetworkSubDependencyGraph
import dagger.Module

@Module(subcomponents = [NetworkSubDependencyGraph::class])
class SubDependencyGraphs