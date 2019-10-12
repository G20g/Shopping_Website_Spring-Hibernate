package com.casestudy.ecart.repository;

import com.casestudy.ecart.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Integer> {
    public List<Items> findAllByCategory(String cat);
    public List<Items> findAllByCategoryAndUnitPriceBetween(String cat,Double c1,Double c2);
    public List<Items> findAllByUnitPriceBetween(Double c1,Double c2);
    public List<Items> findAllByBrandContaining(String b);
    public List<Items> findAllByCategoryAndBrandContaining(String cat,String b);
}

