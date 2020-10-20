package com.kennysexton.sunset

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object _KotlinExtensions {

    fun FragmentManager.replaceFragment(tag: String, fragmentContainerId: Int, addToBackstack: Boolean, fragmentCreator: () -> Fragment) {

        var fragment = this.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = fragmentCreator()
        }


        val transaction = this.beginTransaction().setPrimaryNavigationFragment(fragment)
        transaction.replace(fragmentContainerId, fragment, tag)

        if (addToBackstack) {
            transaction.addToBackStack(tag)
        }

        transaction.commit()
    }
}