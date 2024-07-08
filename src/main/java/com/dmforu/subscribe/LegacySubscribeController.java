//package com.dmforu.subscribe;
//
//import com.dmforu.subscribe.dto.DepartmentDto;
//import com.dmforu.subscribe.dto.DepartmentRequestDto;
//import com.dmforu.subscribe.dto.InitRequestDto;
//import com.dmforu.subscribe.dto.TokenRequestDto;
//import com.dmforu.subscribe.dto.TokensDto;
//import com.dmforu.subscribe.dto.TopicRequestDto;
//import com.dmforu.subscribe.service.Department;
//import com.dmforu.subscribe.service.InitService;
//import com.dmforu.subscribe.service.TokenService;
//import com.google.firebase.messaging.FirebaseMessagingException;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.ExecutionException;
//
//@Tag(name="[구버전] 알림설정")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping
//public class LegacySubscribeController {
//
//    private final InitService initService;
//    private final Department departmentService;
//    private final TokenService tokenService;
//
//    @PostMapping("/token/v1/dmu/initToken")
//    public void createTokenDepartment(@RequestBody InitRequestDto initRequestDto) {
//        initService.createTokenDepartment(initRequestDto);
//    }
//
//    //기존 Token TTL Refresh
//    @PostMapping("/token/v1/dmu/refreshToken")
//    public void refreshTopic(@RequestBody TokenRequestDto tokenRequestDto){
//        tokenService.refreshToken(tokenRequestDto.getToken());
//    }
//
//    @PostMapping("/department/v1/dmu/createDepartment")
//    public void createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
//        departmentService.createDepartment(
//                new DepartmentDto(departmentRequestDto.getToken(), departmentRequestDto.getDepartment())
//        );
//    }
//
//    @PostMapping("/department/v1/dmu/updateDepartment")
//    public void updateDepartment(@RequestBody DepartmentRequestDto departmentDto) {
//        departmentService.updateDepartment(
//                new DepartmentDto(departmentDto.getToken(), departmentDto.getDepartment()));
//    }
//
//    @PostMapping("/department/v1/dmu/deleteDepartment")
//    public void deleteDepartment(@RequestBody TokenRequestDto tokenRequestDto) {
//        departmentService.deleteDepartment(tokenRequestDto.getToken());
//    }
//
//
//    @PostMapping("/token/v1/dmu/createTopic")
//    public void createTopic(@RequestBody TopicRequestDto tokenDto) {
//        tokenService.createTopic(
//                new TokensDto(tokenDto.getToken(), tokenDto.getTopics())
//        );
//    }
//
//    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
//    //messageService.updateToken = Google FCM token & Topic 등록
//    @PostMapping("/token/v1/dmu/updateTopic")
//    public void updateTopic(@RequestBody TopicRequestDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
//        tokenService.updateTopic(
//                new TokensDto(tokensDto.getToken(), tokensDto.getTopics())
//        );
//    }
//
//    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
//    @PostMapping("/token/v1/dmu/deleteTopic")
//    public void deleteTopic(@RequestBody TokenRequestDto tokensDto) {
//        tokenService.deleteTopic(tokensDto.getToken());
//    }
//
//}
package com.dmforu.subscribe;
        import com.dmforu.subscribe.dto.DepartmentDto;
        import com.dmforu.subscribe.dto.DepartmentRequestDto;
        import com.dmforu.subscribe.dto.InitRequestDto;
        import com.dmforu.subscribe.dto.TokenRequestDto;
        import com.dmforu.subscribe.dto.TokensDto;
        import com.dmforu.subscribe.dto.TopicRequestDto;
        import com.dmforu.subscribe.dtoV2.DepartmentDTO;
        import com.dmforu.subscribe.dtoV2.InitTokensDTO;
        import com.dmforu.subscribe.dtoV2.KeywordDTO;
        import com.dmforu.subscribe.service.*;
        import com.google.firebase.messaging.FirebaseMessagingException;
        import io.swagger.v3.oas.annotations.tags.Tag;
        import lombok.RequiredArgsConstructor;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.concurrent.ExecutionException;

@Tag(name="[구버전] 알림설정")
@RestController
@RequiredArgsConstructor
@RequestMapping
public class LegacySubscribeController {

    private final DepartmentService departmentService;
    private final KeywordService tokenService;

    @PostMapping("/token/v1/dmu/initToken")
    public void createTokenDepartment(@RequestBody InitRequestDto initRequestDto) {
        tokenService.createInitTokenInformation(
                new InitTokensDTO(initRequestDto.getToken(), initRequestDto.getDepartment(), initRequestDto.getTopic(), true, true)
        );
    }

    //기존 Token TTL Refresh
    @PostMapping("/token/v1/dmu/refreshToken")
    public void refreshTopic(@RequestBody TokenRequestDto tokenRequestDto){
        tokenService.refreshToken(tokenRequestDto.getToken());
    }

    @PostMapping("/department/v1/dmu/createDepartment")
    public void createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        departmentService.updateDepartment(
                new DepartmentDTO(departmentRequestDto.getToken(), departmentRequestDto.getDepartment())
        );
    }

    @PostMapping("/department/v1/dmu/updateDepartment")
    public void updateDepartment(@RequestBody DepartmentRequestDto departmentDto) {
        departmentService.updateDepartment(
                new DepartmentDTO(departmentDto.getToken(), departmentDto.getDepartment()));
    }

//    @PostMapping("/department/v1/dmu/deleteDepartment")
//    public void deleteDepartment(@RequestBody TokenRequestDto tokenRequestDto) {
//        departmentService.deleteDepartment(tokenRequestDto.getToken());
//    }
//

    @PostMapping("/token/v1/dmu/createTopic")
    public void createTopic(@RequestBody TopicRequestDto tokenDto) {
        tokenService.updateKeyword(
                new KeywordDTO(tokenDto.getToken(), tokenDto.getTopics())
        );
    }

    //redisService.updateToken = Token 유효시간 갱신 및 Token Redis Server에 등록
    //messageService.updateToken = Google FCM token & Topic 등록
    @PostMapping("/token/v1/dmu/updateTopic")
    public void updateTopic(@RequestBody TopicRequestDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
        tokenService.updateKeyword(
                new KeywordDTO(tokensDto.getToken(), tokensDto.getTopics())
        );
    }

//    //Topic 구독 취소, 추후에 무결성 검증 로직 추가
//    @PostMapping("/token/v1/dmu/deleteTopic")
//    public void deleteTopic(@RequestBody TokenRequestDto tokensDto) {
//        tokenService.deleteTopic(tokensDto.getToken());
//    }

}
