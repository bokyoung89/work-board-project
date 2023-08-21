package com.bokyoung.workboardproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST - API 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
public class DataRestTest {

    private MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .build();
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void api_게시글_리스트_조회() throws Exception {
        //given

        //when & then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(document("articles",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                subsectionWithPath("_embedded.articles").description("게시글 목록"),
                                subsectionWithPath("_links").description("링크 목록"),
                                subsectionWithPath("page").description("페이징"),
                                fieldWithPath("_embedded.articles[].title").description("제목"),
                                fieldWithPath("_embedded.articles[].content").description("내용"),
                                fieldWithPath("_embedded.articles[].createdAt").description("작성일"),
                                fieldWithPath("_embedded.articles[].createdBy").description("작성자"),
                                fieldWithPath("_embedded.articles[].modifiedAt").description("수정일"),
                                fieldWithPath("_embedded.articles[].modifiedBy").description("수정자"),
                                fieldWithPath("_embedded.articles[].hashtag").description("해시태그")
                        )
                ));
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void api_게시글_단건_조회() throws Exception {
        //given
        long articleId = 1L;

        //when & then
        mvc.perform(RestDocumentationRequestBuilders.get("/api/articles/{articleId}", articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(document("article",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("articleId").description("게시글 ID")
                        ),
                        responseFields(
                                fieldWithPath("title").description("제목"),
                                fieldWithPath("content").description("내용"),
                                fieldWithPath("createdAt").description("작성일"),
                                fieldWithPath("createdBy").description("작성자"),
                                fieldWithPath("modifiedAt").description("수정일"),
                                fieldWithPath("modifiedBy").description("수정자"),
                                fieldWithPath("hashtag").description("해시태그"),
                                subsectionWithPath("_links").description("게시글 관련 링크")
                        )
                ));
    }

    @DisplayName("[api] 게시글의 댓글 리스트 조회")
    @Test
    void api_게시글의_댓글_리스트_조회() throws Exception {
        //given
        long articleId = 1L;

        //when & then
        mvc.perform(RestDocumentationRequestBuilders.get("/api/articles/{articleId}/articleComments", articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(document("articleComments-of-article",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("articleId").description("게시글 ID")
                        ),
                        responseFields(
                                subsectionWithPath("_embedded.articleComments").description("댓글 목록"),
                                subsectionWithPath("_links").description("링크 목록"),
                                fieldWithPath("_embedded.articleComments[].content").description("내용"),
                                fieldWithPath("_embedded.articleComments[].createdAt").description("작성일"),
                                fieldWithPath("_embedded.articleComments[].createdBy").description("작성자"),
                                fieldWithPath("_embedded.articleComments[].modifiedAt").description("수정일"),
                                fieldWithPath("_embedded.articleComments[].modifiedBy").description("수정자")
                        )
                ));
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void api_댓글_리스트_조회() throws Exception {
        //given

        //when & then
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void api_댓글_단건_조회() throws Exception {
        //given

        //when & then
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @Disabled
    @DisplayName("[api] 회원 관련 API 는 일체 제공하지 않는다.")
    @Test
    void givenNothing_whenRequestingUserAccounts_thenThrowsException() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(post("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(put("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(patch("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(delete("/api/userAccounts")).andExpect(status().isNotFound());
        mvc.perform(head("/api/userAccounts")).andExpect(status().isNotFound());
    }
}
