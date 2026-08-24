-- もしすでに古いテーブルがあったら一度消す
DROP TABLE IF EXISTS kintai_records;

-- 出勤データを保存するための「kintai_records」というテーブル（箱）を作る
CREATE TABLE kintai_records (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY, -- データの背番号（自動で1, 2, 3...と増える）
                                date VARCHAR(10) NOT NULL,            -- 日付（例：2026-08-22）
                                time VARCHAR(5) NOT NULL              -- 時刻（例：09:15）
);
