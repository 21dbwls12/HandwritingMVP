# 필기앱(MVP)

## 📆 개발 기간

2026.01.19 ~ 2026.02.06

## 📝 소개

MVC 패턴과 MVP 패턴을 비교하여 학습하기 위한 프로젝트 중 하나이며, 이 앱은 MVP 패턴을 사용하여 만든 어플입니다.

MVC 패턴과 같은 기능의 필기앱을 만들었습니다. 
원하는 사진을 화면에 표시할 수 있고, 사용자가 화면을 드래그하여 필기할 수 있습니다.

[MVC 패턴 깃헙](https://github.com/21dbwls12/HandwritingMVC)

## ⚙️ 기술 스택

### 📱 App

- <img src="https://img.shields.io/badge/Android Studio-3DDC84?style=plastic&logo=androidstudio&logoColor=white">  <img src="https://img.shields.io/badge/Jetpack Compose-4285F4?style=plastic&logo=jetpackcompose&logoColor=white">
- <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=plastic&logo=kotlin&logoColor=white">
- <img src="https://img.shields.io/badge/Material Design 3-6750A4?style=plastic&logo=materialdesign&logoColor=white">
- <img src="https://img.shields.io/badge/Coil-3B6BB4?style=plastic">


### 🛠️ Tools

[![My Skills](https://skillicons.dev/icons?i=github,notion&theme=light)](https://skillicons.dev)

### 📷 화면 구성

![Screenshot_20260206_222359_HandwritingMVP](https://github.com/user-attachments/assets/57ed62a5-bb85-4fc1-8a6e-ff5c7c07bbf0)|![Screenshot_20260206_222611_HandwritingMVP](https://github.com/user-attachments/assets/a0b0d5c6-f1ef-4a91-ab43-f21b875f93cf)|![Screenshot_20260206_222912_Gallery](https://github.com/user-attachments/assets/248451e5-affe-4aef-83b4-d827924fa594)
|:---:|:---:|:---:|
|가장 처음 화면|업로드 한 이미지|"MVP" 단어를 필기한 화면|
![Screenshot_20260206_223023_Gallery](https://github.com/user-attachments/assets/b7ba9e69-d903-467a-aa31-1ee11b4a5556)|![Screenshot_20260206_223035_Gallery](https://github.com/user-attachments/assets/0eb111ae-4f3c-4dac-8ad6-ec3840b20e81)|![Screenshot_20260206_223043_Gallery](https://github.com/user-attachments/assets/eea79983-c08d-48c5-a54d-83701370c7b8)
|상단에 휴지통 버튼을 누르면 나타나는 대화상자|대화상자의 "아니오" 버튼을 누른 화면|대화상자의 "예"를 누른 화면|
![Screen_Recording_HandwritingMVP_1](https://github.com/user-attachments/assets/6d327c3d-4a40-4842-ab9d-81312090307a)
|필기중인 화면|

### 🎥 시연 영상

https://github.com/user-attachments/assets/036cc236-c058-4e52-9a60-d433d0d16432

https://github.com/user-attachments/assets/4c535a78-5517-441a-b407-7a77e5dbe986

## 📌 더 개선해야하는 점

- 저장
  - 작성한 필기(이미지 포함)를 저장하는 기능을 추가하고 싶습니다.
  - 확장자를 다르게 하여 저장하는 기능도 포함하고 싶습니다.
    - pdf, png, jpg 등
- 노트 및 필기 설정 
  - 선의 두께와 색깔을 사용자가 직접 변경하여 필기할 수 있도록 펜 설정 기능을 추가하고 싶습니다.
  - 배경 색깔 변경 기능과 기본 배경 템플릿(모눈 종이, 다양한 재질)도 추가하고 싶습니다.
- 파일 업로드
  - 이미지를 여러장 선택하여 한번에 표시하도록 하고 싶습니다.
    - 각각 이미지에 다른 필기를 저장하려고 힙니다.
    - 새로 이미지를 추가하는 기능까지 추가하고 싶습니다.(새 이미지를 업로드할 때 원래 있던 이미지는 삭제되지 않음)
  - pdf 파일 자체를 업로드할 수 있도록 하고 싶습니다.
- 실행 취소
  - 필기 작성 중에 실행 취소 기능을 추가하고 싶습니다.
  - 실행 취소한 필기를 다시 복구하는 기능을 추가하고 싶습니다.
- 깃헙 브랜치 관리
  - Git Flow를 적용해서 프로젝트를 진행해보고 싶습니다.
    - 현재는 혼자하는 작업이고 단발성 프로젝트라서 브랜치 관리를 신경쓰지 않았는데 Git 관련해서 정보를 찾다가 브랜치 관리 방법에 대해 알게 되었습니다.
    - 현재의 깃헙 관리에 대해 부족함을 알게 되고 더 체계적인 관리의 필요성을 느끼게 되었습니다.

## ❗️ 배운 점

- MVP 패턴 개념에 대해 학습하였습니다.
  - Model(데이터), View(UI 및 UI 관련 코드), Presenter(Model과 View 연결, UI 흐름 제어)
  - MVP 패턴과 MVC 패턴의 차이에 대해 이해하였습니다.
    - MVC 패턴 구조에 맞게 작업했던 이전 프로젝트의 코드를 MVP 패턴 구조로 변환하면서 MVC 패턴의 Controller과 MVP 패턴의 Presenter의 역할 차이와 두 패턴에서의 View가 다른 구조로 이루어지며 다른 역할을 수행하고 있음을 학습하였습니다.
- 인터페이스의 사용 방법을 경험하며 학습하였습니다.
  - MVP 패턴 구조를 적용하면서 View와 Presenter를 인터페이스로 연결하였고, 이를 통해 인터페이스의 개념을 이해하였습니다.

## ❓ 궁금증

- 기능 구현을 위해 따로 생성한 브랜치에서 main 브랜치로 merge하면서 깃헙 브랜치 관리의 부족함을 인지하고 지금보다 더 체계적인 관리의 필요성을 느꼈습니다.
  - 시간이 지난 후에도 쉽게 알아볼 수 있도록 깃헙 브랜치 관리를 위한 Git Flow 방법과 Github Flow 방법에 대해 더 찾아보고 직접 적용해보고 싶습니다.
  - merge, rebase를 용도에 따라 다르게 사용하기 위해 git merge의 개념과 작동원리에 대한 공부의 필요성을 인지했습니다.
  - 지금은 pull request를 사용하지 않고 바로 merge를 하였는데 pull request를 사용하여 브랜치에 대한 기록을 남기고 깃헙 이슈를 자동으로 닫는 기능을 적용해보고 싶습니다.
- 현재는 presenter와 view를 각각 하나의 클래스만 사용하여 작업했는데 작업하다보니 너무 한 클래스의 부담이 커지고 가독성이 떨어진다는 느낌을 받았습니다. 또, view를 Main Activity를 사용해서 작성하다 보니 더 가독성이 떨어졌습니다. 그래서 각 클래스의 부담을 줄이고 가독성을 높일 수 있는 방법에 대해 고민이 생겼습니다.
  - 어떻게 view와 presenter가 1:1 관계를 유지하면서 분리할 수 있을지에 의문이 생겼고, 분리할 범위에 대해 고민하게 되었습니다.
