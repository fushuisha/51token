import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试环境连胜假数据
 */
public class BattleTblSqlGenerator3 {
    // delete from mgame_battle_tbl where id>=3324;
//    final static int battleIdStartNum = 4626;
    // delete from mgame_user_tbl where id>=108;
    // delete from mgame_player_tbl where id>=108;
    final static int userIdStartNum = 108;
    final static int matchId = 55;

    final static String templateInsertThreadWinSql = "INSERT INTO `mgame`.`mgame_thread_win_tbl` " +
            "(`user_id`,  `match_id`,  `win_times`, `win_stop`, `win_id`, `stop_id`, `top_win_times`,  `del_flg`, `version`, `create_user`, `create_time`,   `update_user`, `update_time`) VALUES " +
            "('{user_id}','{match_id}','1',         '0',        '0',      '0',       '{top_win_times}','0',       '1',       '0',           '{create_time}', '0',           '{update_time}');";

    public static void main(String[] args) throws Exception {
        File battleTblSqlGeneratorFile = new File("d:\\tmp\\battleTblSql3.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(battleTblSqlGeneratorFile, true);
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 60; i++) {
            String userIdStr = (userIdStartNum + i) + "";
            String matchIdStr = matchId + "";
            String sql = templateInsertThreadWinSql
                    .replace("{user_id}", userIdStr)
                    .replace("{match_id}", matchIdStr)
                    .replace("{top_win_times}", (i + 5) + "")
                    .replace("{create_time}", simpleTimeFormat.format(new Date()))
                    .replace("{update_time}", simpleTimeFormat.format(new Date()));
            IOUtils.write(sql + "\n", fileOutputStream, "utf-8");
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }


}
