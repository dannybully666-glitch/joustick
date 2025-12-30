package com.joystickapp.profile;

import org.json.JSONObject;

public class GameProfile {
    public String name;
    public JSONObject mappings;

    public static GameProfile fromJson(JSONObject o) {
        GameProfile p = new GameProfile();
        p.name = o.optString("name");
        p.mappings = o.optJSONObject("mappings");
        return p;
    }
}
