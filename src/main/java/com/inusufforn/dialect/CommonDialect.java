/**
 * ダイアレクト共有ライブラリ
 * 
 * Copyright 2022 by isaku, All rights reserved.
 */
package com.inusufforn.dialect;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 * 共有ライブラリダイアレクトクラス.
 * 
 * @author isaku
 *　@version 1.0
 * 
 */
@Component
public class CommonDialect extends AbstractProcessorDialect {

    /** ダイアレクト名 */
    private static final String NAME = "Shared library of dialect";
    /** プレフィックス */
    private static final String PREFIX = "com";

    /** コンストラクタ */
    public CommonDialect() {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    /**
    * @inheritDoc
    */
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<>();

        // プロセッサのインスタンスのSetを生成
        processors.add(new BreakLineProcessor(dialectPrefix, getDialectProcessorPrecedence()));
        return processors;
    }

}
