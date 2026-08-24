package com.example.kintai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "kintai_records")
@Data
public class KintaiRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date; // 日付を保存する変数
    private String time; // 出勤時刻を保存する変数（分かりやすいように名前はそのままにします）
    private String taikinTime; // 👈【新しく追加】退勤時刻を保存する変数
}
