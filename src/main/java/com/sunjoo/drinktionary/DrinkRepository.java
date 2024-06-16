package com.sunjoo.drinktionary;

import com.sunjoo.drinktionary.entity.Drink;
import com.sunjoo.drinktionary.entity.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    // 주류 전체 조회
    List<Drink> findAll();
    // 주류 개별 조회
    List<Drink> findByName(String name);
    // 주류 종류별 조회
    List<Drink> findByType(DrinkType type);
}
