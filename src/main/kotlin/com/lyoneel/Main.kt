package com.lyoneel

import org.json.JSONObject
import java.net.URL

fun main(vararg args: String) {

    var versionMetadataUrl = ""
    val globalMetadata = getJsonFileAsJsonObject(getJsonUrl())
    val version = globalMetadata.getJSONObject("latest").get("release")

    if (args.isNotEmpty() && args.size == 1 && paramsContains(args, "version") ) {
        println(version)
    } else {
        val versionsObjects = globalMetadata.getJSONArray("versions")
        for (versionObject in versionsObjects) {
            if(versionObject is JSONObject){
                if(versionObject.getString("id") == version && versionObject.getString("type") == "release"){
                    versionMetadataUrl = versionObject.getString("url")
                    break
                }
            }
        }
        val server = getJsonFileAsJsonObject(versionMetadataUrl)
            .getJSONObject("downloads")
            .getJSONObject("server")
        val jarUrl = server.getString("url")
        val sha1 = server.getString("sha1")
        val size = server.getInt("size")

        if (args.isEmpty()){
            println(version)
            println(jarUrl)
            println(sha1)
            println(size)
        } else {
            if(paramsContains(args, "version")){
                println(version)
            }
            if(paramsContains(args, "url")){
                println(jarUrl)
            }
            if(paramsContains(args, "sha") || paramsContains(args, "sha1")){
                println(sha1)
            }
            if(paramsContains(args, "size")){
                println(size)
            }
        }
    }
}

private fun getJsonUrl(): String {
    val result: String
    if (System.getProperty("MC_MAIN_JSON_URL") != null) {
        result = System.getProperty("MC_MAIN_JSON_URL")
    } else {
        result = "https://launchermeta.mojang.com/mc/game/version_manifest.json"
    }
    return result
}

fun paramsContains(arguments: Array<out String>, s: String): Boolean {
    return arguments.any { e -> e.equals(s, ignoreCase = true) }
}

fun getJsonFileAsJsonObject(url: String): JSONObject {
    return JSONObject(URL(url).readText(Charsets.UTF_8))
}