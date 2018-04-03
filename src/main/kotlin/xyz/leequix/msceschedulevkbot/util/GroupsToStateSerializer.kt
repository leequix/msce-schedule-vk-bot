package xyz.leequix.msceschedulevkbot.util

import org.json.JSONArray

abstract class GroupsToStateSerializer {
    companion object {
        fun serialize(groupNumbers: List<String>) =
                JSONArray(groupNumbers).toString()
        fun deserialize(json: String) = JSONArray(json).toList().map { it.toString() }
    }
}