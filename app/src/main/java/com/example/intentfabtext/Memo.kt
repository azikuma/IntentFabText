package com.example.intentfabtext

import io.realm.RealmObject

open class Memo: RealmObject() {
    var text: String = ""
}