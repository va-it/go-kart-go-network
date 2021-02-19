package go_kart_go_network;

public class Messages {
    public static String establishConnection = "establish_connection";
    public static String connectionSuccessful = "connection_successful";
    public static String getPlayerNumber = "get_player_number";
    public static String startRace = "start_race";
    public static String stopRace = "stop_race";
    public static String closeConnection = "close_connection";
    public static String sendingKartInfo = "sending_kart_info";
    public static String kartInfoReceived = "kart_info_received";

    public static String getOpponentIndex(int player) {
        return "get_player_" + player + "_index";
    }
    public static String getOpponentSpeed(int player) {
        return  "get_player_" + player + "_speed";
    }

    public static String returnPlayerNumber(int player) { return String.valueOf(player); }
}
