package com.eyesofkhepri.springbootjooq.service;

import com.eyesofkhepri.springbootjooq.domain.LanguageModel;
import com.eyesofkhepri.springbootjooq.generate.tables.Language;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BindModelData {

    @Autowired
    DSLContext dsl;

    /**
     * fetchInto를 사용해서 특정 데이터에 Binding이 되는지 테스트 한다.
     */
    @Test
    public void testBindLanguageModel() {
        List<LanguageModel> l = dsl.select(Language.LANGUAGE.ID, Language.LANGUAGE.CD, Language.LANGUAGE.DESCRIPTION)
                .from(Language.LANGUAGE)
        .fetchInto(LanguageModel.class);

        l.stream().forEach(System.out::println);
    }
}
