
import com.token51.util.CommonUtils;
import com.token51.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDW on 2018/4/21.
 */
public class RemoveFileTester {
    public static void main(String[] args) throws Exception {
        File baseFold = new File("d:\\bt\\complete");
        List<File> needRemoveFileList = new ArrayList<>();
        List<String> has3FilesFold = new ArrayList<>();
        pickupRemoveFile(baseFold, needRemoveFileList, has3FilesFold);
        String listString = JacksonUtils.toPrettyJson(has3FilesFold);
        System.out.println("has3FilesFold:" + listString);
        listString = JacksonUtils.toPrettyJson(needRemoveFileList);
        System.out.println("needRemoveFileList:" + listString);
        if (CommonUtils.isValidCollect(needRemoveFileList)) {
            for (File file : needRemoveFileList) {
                file.delete();
            }
        }
    }

    private static void pickupRemoveFile(File baseFold, List<File> needRemoveFileList, List<String> has3FilesFold) {
        if (baseFold.isDirectory()) {
            File[] files = baseFold.listFiles();
            if (CommonUtils.isValidCollect(files)) {
                if (files.length > 2) {
                    has3FilesFold.add(baseFold.getName());
                }
                for (File file : files) {
                    if (file.isFile()) {
                        String name = file.getName();
                        if (StringUtils.endsWithAny(name, ".mht", ".url", ".chm", ".html", ".txt", ".lnk", ".gif", ".COM.jpg")
                                || StringUtils.startsWithAny(name, "1024", "_1024"
                                , "【魔王の家】", "★★★★", "mo9999", "mo6699", "mo7777", "mo888", "嫑嫑", "裸聊直播",
                                "美女荷官", "台湾", "QQ群微信", "波多野", "加入微信號", "QR-1024.jpg", "HOTAVXXX", "99BTGC.jpg",
                                "超高画质AV", "史上最强AV", "AV资源站", "zz.jpg", "祼聊室注册成为", "台湾", "hjav.in~",
                                "可以指揮辣妹潮吹", "辣妹裸聊陪伴", "免费手机看片", "免費的手机看片新片每日火熱更新中",
                                "台灣", "同城找模特", "線上影片每天火熱更新中", "BIO马来西亚激情咖啡", "超高清手機", "启动迅雷7.lnk",
                                "寻找同城", "加入會員馬上免費試看AV", "华人美女荷官", "美女校花嫩模", "最強大超高清", "最强大超高清",
                                "绝对信誉", "美女免費", "_____padding", "更多的辣妹", "全新改最终版", "最终版美女校花",
                                "Sky.jpg", "QR-001.jpg", "QR-002.jpg", "QR-sky.jpg", "QR-code.jpg", "206.108.51.3-最低價點數大放送.jpg",
                                "藏精閣-地球上最強影音網站 - AV9.CC.jpg", "03.週年慶下殺大特價DVD每片20元~.png", "05.MYAV.png",
                                "05.jp.myav.tv.png", "AC227.jpg", "BY336.jpg", "BBK38.jpg", "TP69.jpg", "yy.jpg", "mo777.net~",
                        "免费翻墙代理-【1024vpn.net】.jpg","淫乱诱惑妈妈自慰 校花做爱巨乳肉便器.zip","扫一扫看片.jpg","A片资源站www.909zy.net.jpg",
                                "03.閫卞勾鎱朵笅娈哄ぇ鐗瑰児DVD姣忕墖20鍏儈.png","水舞间.avi","美女真人激情視頻.avi","blue.jpg",
                                "2048最新地址 UC瀏覽器掃.png","小白加速器免费翻墙官网 - 最快最稳定的加速器.URL","_QR-1024.jpg",
                                "爆強國產合輯.htm","國產自拍_蘿莉寫真_網紅嫩模.htm","uuf83.mp4","vip2209鳳凰娛樂城.mp4",
                                "校花嫩模激情裸聊加微信903191919 按微信名字操作加QQ软件即可试看.mp4")
                                || StringUtils.contains(name, "防屏蔽程序")) {
                            needRemoveFileList.add(file);
                        }
                    } else {
                        pickupRemoveFile(file, needRemoveFileList, has3FilesFold);
                    }
                }
            }
        }
    }
}
