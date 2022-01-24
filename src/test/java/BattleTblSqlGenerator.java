import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;

public class BattleTblSqlGenerator {
//    final static int idStartNum = 2500;
    final static int battleIdStartNum = 2500;
    final static int userIdStartNum = 1;
    final static String[] playerNameArray = {"", "未設定1",
            "曹先生1",
            "曹先生",
            "12345678901234567890",
            "虞老师1",
            "hch25",
            "hchPC",
            "虞老师3",
            "gj",
            "未設定2",
            "虞亦飞1234567890",
            "未設定3",
            "未設定4",
            "未設定5",
            "未設定6",
            "未設定7",
            "未設定8",
            "未設定9",
            "鳥元",
            "曹先生2",
            "hch2233",
            "曹先生11",
            "曹先生12",
            "曹先生13",
            "曹先生14",
            "hch001",
            "未設定11",
            "未設定12",
            "未設定13",
            "未設定14",
            "未設定15",
            "5145",
            "未設定16",
            "未設定17",
            "山神",
            "曹先生15",
            "未設定18",
            "未設定19",
            "未設定20",
            "未設定21",
            "未設定22",
            "未設定23",
            "曹先生16",
            "未設定24",
            "未設定25",
            "testchi",
            "未設定26",
            "焼肉",
            "testchi2",
            "sha",
            "guode",
            "未設定27",
            "未設定28",
            "未設定29",
            "Samyu",
            "曹先生m",
            "未設定30",
            "yyf45678901234567890",
            "曹先生20",
            "未設定31",
            "未設定32",
            "未設定33",
            "未設定34",
            "hch007",
            "name",
            "未設定35",
            "未設定36",
            "hchhu",
            "未設定37",
            "未設定38",
            "goodman",
            "good3",
            "good2",
            "good1",
            "未設定39",
            "曹先生曹先生曹先生曹",
            "未設定40",
            "ryg",
            "新测试",
            "yyf1",
            "未設定41",
            "测试120",
            "noodles",
            "测试666",
            "测试112",
            "yyfmobile",
            "未設定42",
            "测试250",
            "未設定43",
            "未設定44",
            "真曹先生",
            "未設定45",
            "ddddd",
            "yyf0",
            "Xperformance",};
    final static String templateInsertSql1 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`id`, `match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{id}', '47', '{battle_id}', '{user_id}', '1', '0', '30', '', '{battle_date}', '{battle_time}', " +
            "'2', '3', '16', '100', '0', '28', '1', '80', '80', " +
            "'80', '0', '0', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', " +
            "'10', '10', '10', '10', '10', '10', '10', '10', '0', " +
            "'10', '10', '10', '10', '10', '10', '10', '10', '0', " +
            "'247.5', '0', '1', '0', '0000-00-00 00:00:00', '0', '{update_time}', '{battle_end_time}', '{player_name}');";
    final static String templateInsertSql2 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`id`, `match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{id}', '47', '{battle_id}', '{user_id}', '1', '0', '30', '', '2020-08-28', '2020-08-28 19:05:11', " +
            "'2', '3', '16', '100', '0', '28', '2', '80', '70', " +
            "'70', '0', '10', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', '0.00', " +
            "'10', '10', '10', '10', '10', '10', '10', '0', '0', " +
            "'10', '10', '10', '10', '10', '10', '10', '0', '0', " +
            "'247.5', '0', '1', '0', '0000-00-00 00:00:00', '0', '2020-08-28 19:09:11', '2020-08-28 19:09:11', '{player_name}');";
    final static String templateInsertSql3 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`id`, `match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{id}', '47', '{battle_id}', '{user_id}', '1', '0', '30', '', '2020-08-28', '2020-08-28 19:05:11', " +
            "'2', '3', '16', '100', '0', '28', '3', '80', '60', " +
            "'60', '0', '20', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', '0.00', '0.00', " +
            "'10', '10', '10', '10', '10', '10', '0', '0', '0', " +
            "'10', '10', '10', '10', '10', '10', '0', '0', '0', " +
            "'247.5', '0', '1', '0', '0000-00-00 00:00:00', '0', '2020-08-28 19:09:11', '2020-08-28 19:09:11', '{player_name}');";
    final static String templateInsertSql4 = "INSERT INTO `mgame`.`mgame_battle_tbl` " +
            "(`id`, `match_id`, `battle_id`, `user_id`, `fee_type`, `fee_ticket`, `fee_chip`, `name`, `battle_date`, `battle_time`, " +
            "`chance`, `upgrade_flg`, `score`, `point`, `chip`, `ranking_point`, `battle_ranking`, `quiz_amount`, `kaito_amount`, " +
            "`right_amount`, `wrong_amount`, `pass_amount`, `right_rate`, `channel1_rate`, `channel2_rate`, `channel3_rate`, `channel4_rate`, `channel5_rate`, `channel6_rate`, `channel7_rate`, `channel8_rate`, `channel9_rate`, " +
            "`channel1_kaito`, `channel2_kaito`, `channel3_kaito`, `channel4_kaito`, `channel5_kaito`, `channel6_kaito`, `channel7_kaito`, `channel8_kaito`, `channel9_kaito`, " +
            "`channel1_right`, `channel2_right`, `channel3_right`, `channel4_right`, `channel5_right`, `channel6_right`, `channel7_right`, `channel8_right`, `channel9_right`, " +
            "`average_time`, `del_flg`, `version`, `create_user`, `create_time`, `update_user`, `update_time`, `battle_end_time`, `player_name`) " +
            "VALUES " +
            "('{id}', '47', '{battle_id}', '{user_id}', '1', '0', '30', '', '2020-08-28', '2020-08-28 19:05:11', " +
            "'2', '3', '16', '100', '0', '28', '4', '80', '50', " +
            "'50', '0', '30', '100.00', '100.00', '100.00', '100.00', '100.00', '100.00', '0.00', '0.00', '0.00', '0.00', " +
            "'10', '10', '10', '10', '10', '0', '0', '0', '0', " +
            "'10', '10', '10', '10', '10', '0', '0', '0', '0', " +
            "'247.5', '0', '1', '0', '0000-00-00 00:00:00', '0', '2020-08-28 19:09:11', '2020-08-28 19:09:11', '{player_name}');";

    public static void main(String[] args) throws Exception {
        File battleTblSqlGeneratorFile = new File("d:\\tmp\\battleTblSql.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(battleTblSqlGeneratorFile, true);
        for (int i = 0; i < 23; i++) {
            String sql1 = templateInsertSql1
//                    .replace("{id}", (idStartNum + i * 4 + 0) + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdStartNum + i * 4 + 0) + "")
                    .replace("{player_name}", playerNameArray[userIdStartNum + i * 4 + 0]);
            String sql2 = templateInsertSql2
//                    .replace("{id}", (idStartNum + i * 4 + 1) + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdStartNum + i * 4 + 1) + "")
                    .replace("{player_name}", playerNameArray[userIdStartNum + i * 4 + 1]);
            String sql3 = templateInsertSql3
//                    .replace("{id}", (idStartNum + i * 4 + 2) + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdStartNum + i * 4 + 2) + "")
                    .replace("{player_name}", playerNameArray[userIdStartNum + i * 4 + 2]);
            String sql4 = templateInsertSql4
//                    .replace("{id}", (idStartNum + i * 4 + 3) + "")
                    .replace("{battle_id}", (battleIdStartNum + i) + "")
                    .replace("{user_id}", (userIdStartNum + i * 4 + 3) + "")
                    .replace("{player_name}", playerNameArray[userIdStartNum + i * 4 + 3]);
            IOUtils.write(sql1 + "\n", fileOutputStream, "utf-8");
            IOUtils.write(sql2 + "\n", fileOutputStream, "utf-8");
            IOUtils.write(sql3 + "\n", fileOutputStream, "utf-8");
            IOUtils.write(sql4 + "\n", fileOutputStream, "utf-8");
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
