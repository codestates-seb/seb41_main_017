# seb41_main_017

# Git, Board 컨벤션

-커밋 앞에는 이슈번호를 추가하여 작성한다

```bash
$ git commit -m '**#1 -feat : 커밋메세지'**
```
| 타입 | 설명 |
| --- | --- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 formatting, 세미콜론(;) 누락, 코드 변경이 없는 경우 |
| refactor | 코드 리팩터링 |
| test | 테스트 코드, 리팩터링 테스트 코드 추가(프로덕션 코드 변경 X) |
| chore | 빌드 업무 수정, 패키지 매니저 수정(프로덕션 코드 변경 X), 이미지 파일 업로드, 커밋 타입 중 해당 사항이 없을 때  |
| design | CSS 등 사용자 UI 디자인 변경 |
| comment | 필요한 주석 추가 및 변경 |
| rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 |
| remove | 파일을 삭제하는 작업만 수행한 경우 |
| !breaking change | 커다란 API 변경의 경우 |
| !hotfix | 급하게 치명적인 버그를 고쳐야 하는 경우 |
| misc | merge 충돌해결 |
