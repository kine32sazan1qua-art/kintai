package com.example.kintai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class KintaiController {

    @Autowired
    private KintaiRepository kintaiRepository;

    /**
     * 画面を最初に表示する処理 (http://localhost:8081/shukkin)
     */
    @GetMapping("/shukkin")
    public String showPage(Model model) {
        List<KintaiRecord> historyList = kintaiRepository.findAll();
        model.addAttribute("historyList", historyList);
        return "index";
    }

    /**
     * 「出勤」ボタンが押されたときの保存処理
     */
    @PostMapping("/shukkin")
    public String doShukkin(Model model) {
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        KintaiRecord newRecord = new KintaiRecord();
        newRecord.setDate(currentDate);
        newRecord.setTime(currentTime);

        kintaiRepository.save(newRecord);

        return "redirect:/shukkin";
    }

    /**
     * 👈【新しく追加】「退勤」ボタンが押されたときの処理
     */
    @PostMapping("/taikin")
    public String doTaikin(Model model) {
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        // データベースからすべての履歴を取得
        List<KintaiRecord> historyList = kintaiRepository.findAll();

        // 今日の最新の出勤データを探す（一番最後に登録された今日のデータ）
        KintaiRecord todayRecord = null;
        for (KintaiRecord rec : historyList) {
            if (currentDate.equals(rec.getDate())) {
                todayRecord = rec; // 今日のデータが見つかったら上書き用にキープ
            }
        }

        // もし今日のデータが見つかれば、退勤時刻をセットして更新保存する
        if (todayRecord != null) {
            todayRecord.setTaikinTime(currentTime);
            kintaiRepository.save(todayRecord); // 同じIDなので上書き（UPDATE）になります
        } else {
            // もし出勤ボタンを押さずに退勤を押した場合は、新規でデータを作成
            KintaiRecord newRecord = new KintaiRecord();
            newRecord.setDate(currentDate);
            newRecord.setTaikinTime(currentTime);
            kintaiRepository.save(newRecord);
        }

        return "redirect:/shukkin";
    }
}
