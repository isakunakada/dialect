/**
 * ダイアレクト共有ライブラリ
 *
 * Copyright 2022 by isaku, All rights reserved.
 */
package com.inusufforn.dialect;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.model.ITemplateEvent;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * BRタグ挿入ダイアレクト プロセッサ.
 * 
 * @author isaku
 *　@version 1.0
 * 
 */
public class BreakLineProcessor extends AbstractStandardExpressionAttributeTagProcessor {

    private static final String ATTR_NAME = "brtext";

    public BreakLineProcessor(String dialectPrefix, int precedence) {
        super(TemplateMode.HTML, dialectPrefix, ATTR_NAME, precedence, true);
    }

    /**
    * @inheritDoc
    */
    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
            String attributeValue, Object expressionResult, IElementTagStructureHandler structureHandler) {
        // nullの場合は何も出力せず処理を終了
        if (expressionResult == null) {
            return;
        }

        IModelFactory factory = context.getModelFactory();
        IModel model = factory.createModel();

        ITemplateEvent brTag = factory.createOpenElementTag("br");

        // 改行コードで分割
        String[] lines = expressionResult.toString().split("\r\n|\r|\n", -1);

        // 末尾にbrタグを付加した各行の文字列をmodelに追加
        model.add(factory.createText(lines[0]));
        for (int i=1; i<lines.length; i++) {
            model.add(brTag);
            model.add(factory.createText(lines[i]));
        }

        structureHandler.setBody(model, false);        
    }

}
