package go_kart_go_network;

public class Messages {

    public static final String timeout = "timeout";

    public static final String establishConnection = "establish_connection";
    public static final String connectionSuccessful = "connection_successful";
    public static final String getPlayerNumber = "get_player_number";

    public static final String ready = "ready";
    public static final String readyReceived = "ready_received";
    public static final String requestToStart = "request_to_start";
    public static final String wait = "wait";
    public static final String startRace = "start_race";
    public static final String stopRace = "stop_race";
    public static final String confirmRaceStopped = "race_stopped";
    public static final String closeConnection = "close_connection";

    public static final String running = "running";

    public static final String sendingKartInfo = "sending_kart";
    public static final String getOpponentKartInfo(int player) { return "get_kart_" + player; }

//    public static final String getOpponentIndex = "get_opponent_index";
//    public static final String getOpponentSpeed = "get_opponent_speed";

    public static final String returnPlayerNumber(int player) { return String.valueOf(player); }
    public static final String returnSpeed(int speed) { return String.valueOf(speed); }
    public static final String returnIndex(int index) { return String.valueOf(index); }


//    public static final String sendingKartInfo(int player) { return "sending_player_"+player+"_info"; }
//    public static final String readyToReceiveKart(int player) { return "ready_for_player_"+player+"_info"; }
//    public static final String kartInfoReceived(int player) { return "player_"+player+"_info_received"; }
//    public static final String getOpponentIndex(int player) { return "get_player_"+player+"_opponent_index"; }
//    public static final String getOpponentSpeed(int player) { return "get_player_"+player+"_opponent_speed"; }

    public enum Protocols {
        UDP, TCP
    }
}
