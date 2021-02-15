package go_kart_go_network;

public class Messages {
    public static String startRace = "start_race";
    public static String stopRace = "stop_race";

    public static String getOpponentIndex(int player) {
        return "get_player_" + player + "_index;";
    }

    public static String getOpponentSpeed(int player) {
        return  "get_player_" + player + "_speed;";
    }

    public static String kartInfo(int player, int speed, int index) {
        return "player:" + player + ";speed:" + speed + ";index:" + index + ";";
    }
}
