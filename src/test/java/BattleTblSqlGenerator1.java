import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 测试环境各channel的假数据
 */
public class BattleTblSqlGenerator1 {
    // delete from mgame_battle_tbl where id>=3324;
    final static int battleIdStartNum = 3326;
    // delete from mgame_user_tbl where id>=108;
    // delete from mgame_player_tbl where id>=108;
    final static int userIdStartNum = 108;
    final static int matchId = 55;


    final static String templateInsertUserSql = "INSERT INTO `mgame`.`mgame_user_tbl` " +
            "(`id`, `role_id`, `point`, `chip`, `level`, `rank_point`, `score`, `rank`, `mobile`, `twitter`, `facebook`, `email`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `paid_chip`) VALUES " +
            "('{user_id}', '0', '0', '1000', '1', '{rank_point}', '{score}', 'C-', '', '', '', '', '0', '1', '0', '2020-10-31 16:41:21', '0', '2020-10-31 16:41:21', '0');";
    final static String templateInsertPlayerSql = "INSERT INTO `mgame`.`mgame_player_tbl` " +
            "(`id`, `user_id`, `player_code`, `player_name`, `player_icon`, `player_icon_customer`, `player_icon_bgc`, `player_icon_bgc_customer`, `player_icon_rank`, `player_quick_btn`, `player_stamp_set`, `player_badge_set`, `player_facility`, `player_medal`, `status`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `player_device`, `head_icon`, `agreement_flg`) VALUES " +
            "('{player_id}', '{user_id}', '{player_code}', '{player_name}', '0', '0', '15759665410243000', '0', '0', '15759664141167000', '[{\\\"id\\\":34,\\\"itemId\\\":15759665998494000},{\\\"id\\\":31,\\\"itemId\\\":15759666113689000},{\\\"id\\\":28,\\\"itemId\\\":15759666217901000},{\\\"id\\\":26,\\\"itemId\\\":15759666315911000},{\\\"id\\\":22,\\\"itemId\\\":15759666410085000},{\\\"id\\\":19,\\\"itemId\\\":15759666492234000},{\\\"id\\\":18,\\\"itemId\\\":15759666577978000},{\\\"id\\\":17,\\\"itemId\\\":15759666771553000},{\\\"id\\\":16,\\\"itemId\\\":15759666918943000}]', '\\'0\\'', '{player_facility}', '0', '1', '0', '1', '0', '2020-10-31 16:41:21', '1', '2020-10-31 18:25:16', '0', '15759665264101000', '0');";
    final static String templateInsertSql1 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{match_id}', '{battle_id}', '{user_id}', '1', '0', '30', '', '{battle_date}', '{battle_time}', " +
            "'2', '3', '16', '100', '0', '28', '1', '80', '80', " +
            "'80', '0', '0', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', " +
            "'10', '10', '10', '10', '10', '10', '10', '10', '0', " +
            "'10', '10', '10', '10', '10', '10', '10', '10', '0', " +
            "'247.5', '0', '1', '0', '{create_time}', '0', '{update_time}', '{battle_end_time}', '{player_name}');";
    final static String templateInsertSql2 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{match_id}', '{battle_id}', '{user_id}', '1', '0', '30', '', '{battle_date}', '{battle_time}', " +
            "'2', '3', '16', '100', '0', '28', '2', '80', '70', " +
            "'70', '0', '10', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', '0.00', " +
            "'10', '10', '10', '10', '10', '10', '10', '0', '0', " +
            "'10', '10', '10', '10', '10', '10', '10', '0', '0', " +
            "'247.5', '0', '1', '0', '{create_time}', '0', '{update_time}', '{battle_end_time}', '{player_name}');";
    final static String templateInsertSql3 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{match_id}', '{battle_id}', '{user_id}', '1', '0', '30', '', '{battle_date}', '{battle_time}', " +
            "'2', '3', '16', '100', '0', '28', '3', '80', '60', " +
            "'60', '0', '20', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', '0.00', '0.00', " +
            "'10', '10', '10', '10', '10', '10', '0', '0', '0', " +
            "'10', '10', '10', '10', '10', '10', '0', '0', '0', " +
            "'247.5', '0', '1', '0', '{create_time}', '0', '{update_time}', '{battle_end_time}', '{player_name}');";
    final static String templateInsertSql4 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{match_id}', '{battle_id}', '{user_id}', '1', '0', '30', '', '{battle_date}', '{battle_time}', " +
            "'2', '3', '16', '100', '0', '28', '4', '80', '50', " +
            "'50', '0', '30', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', '0.00', '0.00', '0.00', " +
            "'10', '10', '10', '10', '10', '0', '0', '0', '0', " +
            "'10', '10', '10', '10', '10', '0', '0', '0', '0', " +
            "'247.5', '0', '1', '0', '{create_time}', '0', '{update_time}', '{battle_end_time}', '{player_name}');";

    public static void main(String[] args) throws Exception {
        File battleTblSqlGeneratorFile = new File("d:\\tmp\\battleTblSql1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(battleTblSqlGeneratorFile, true);
        Map<String, String> playerNameArray = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String userIdStr = (userIdStartNum + i) + "";
            String playerName = "テスト" + (userIdStartNum + i);
            playerNameArray.put(userIdStr, playerName);
            String sqlUser = templateInsertUserSql
                    .replace("{user_id}", userIdStr)
                    .replace("{rank_point}", (10000 + i) + "")
                    .replace("{score}", (1000 + i) + "");
            String sqlPlayer = templateInsertPlayerSql
                    .replace("{player_id}", userIdStr)
                    .replace("{user_id}", userIdStr)
                    .replace("{player_code}", genPlayerCode())
                    .replace("{player_name}", playerName)
                    .replace("{player_facility}", DigestUtils.md5Hex(playerName));
            IOUtils.write(sqlUser + "\n", fileOutputStream, "utf-8");
            IOUtils.write(sqlPlayer + "\n", fileOutputStream, "utf-8");
        }
        // TODO 查一下matchid和battleid
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 1300; i++) {
            long ct = System.currentTimeMillis();
            String battleDate = simpleDateFormat.format(ct);
            String battleTime = simpleTimeFormat.format(ct + i * 1000L);
            Integer userIdNum = userIdStartNum + (i % 25) * 4;
            String sql1 = templateInsertSql1
//                    .replace("{id}", (idStartNum + i * 4 + 0) + "")
                    .replace("{match_id}", matchId + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdNum + 0) + "")
                    .replace("{battle_date}", battleDate)
                    .replace("{battle_time}", battleTime)
                    .replace("{create_time}", battleTime)
                    .replace("{update_time}", battleTime)
                    .replace("{battle_end_time}", battleTime)
                    .replace("{player_name}", playerNameArray.get((userIdNum + 0) + ""));
            String sql2 = templateInsertSql2
//                    .replace("{id}", (idStartNum + i * 4 + 1) + "")
                    .replace("{match_id}", matchId + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdNum + 1) + "")
                    .replace("{battle_date}", battleDate)
                    .replace("{battle_time}", battleTime)
                    .replace("{create_time}", battleTime)
                    .replace("{update_time}", battleTime)
                    .replace("{battle_end_time}", battleTime)
                    .replace("{player_name}", playerNameArray.get((userIdNum + 1) + ""));
            String sql3 = templateInsertSql3
//                    .replace("{id}", (idStartNum + i * 4 + 2) + "")
                    .replace("{match_id}", matchId + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdNum + 2) + "")
                    .replace("{battle_date}", battleDate)
                    .replace("{battle_time}", battleTime)
                    .replace("{create_time}", battleTime)
                    .replace("{update_time}", battleTime)
                    .replace("{battle_end_time}", battleTime)
                    .replace("{player_name}", playerNameArray.get((userIdNum + 2) + ""));
            String sql4 = templateInsertSql4
//                    .replace("{id}", (idStartNum + i * 4 + 3) + "")
                    .replace("{match_id}", matchId + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdNum + 3) + "")
                    .replace("{battle_date}", battleDate)
                    .replace("{battle_time}", battleTime)
                    .replace("{create_time}", battleTime)
                    .replace("{update_time}", battleTime)
                    .replace("{battle_end_time}", battleTime)
                    .replace("{player_name}", playerNameArray.get((userIdNum + 3) + ""));
            IOUtils.write(sql1 + "\n", fileOutputStream, "utf-8");
            IOUtils.write(sql2 + "\n", fileOutputStream, "utf-8");
            IOUtils.write(sql3 + "\n", fileOutputStream, "utf-8");
            IOUtils.write(sql4 + "\n", fileOutputStream, "utf-8");
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static String genPlayerCode() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] A_Z = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        stringBuilder.append(random.nextInt(10) + "");
        stringBuilder.append(A_Z[random.nextInt(24)]);
        stringBuilder.append(A_Z[random.nextInt(24)]);
        stringBuilder.append(A_Z[random.nextInt(24)]);
        stringBuilder.append(A_Z[random.nextInt(24)]);
        stringBuilder.append(A_Z[random.nextInt(24)]);
        stringBuilder.append(random.nextInt(10) + "");
        stringBuilder.append(random.nextInt(10) + "");

        return stringBuilder.toString();
    }

}
