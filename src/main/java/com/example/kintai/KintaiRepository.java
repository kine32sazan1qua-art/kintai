package com.example.kintai;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KintaiRepository extends JpaRepository<KintaiRecord, Long> {
    // これでデータベースの受付窓口（操縦席）が完成です！
}
