# KidsCalculator 儿童计算器

一款面向 5-6 岁幼儿园大班儿童的数学练习应用，支持 20 以内减法训练。

## 功能特性

- **减法练习** - 随机生成 20 以内减法题目
- **即时反馈** - 答对/答错语音提示（TTS 中文语音）
- **进度追踪** - 实时显示答题进度和正确率

## 架构设计

```
app/src/main/java/com/kidscalc/app/
├── KidsCalculatorApp.kt       # 主应用入口 (Composable)
├── MainActivity.kt            # Android Activity 入口
├── data/
│   ├── QuizEngine.kt         # 题库引擎 (题目生成/答案校验)
│   └── SoundManager.kt        # 音效管理 (TTS + 系统音效降级)
├── viewmodel/
│   └── QuizViewModel.kt       # MVVM ViewModel (状态管理)
└── ui/
    ├── screens/
    │   ├── HomeScreen.kt     # 首页
    │   ├── QuizScreen.kt     # 答题页
    │   └── ResultScreen.kt    # 结果页
    └── theme/
        ├── Color.kt          # 主题颜色
        └── Theme.kt          # Compose Theme
```

## 技术栈

| 维度 | 技术 |
|------|------|
| 语言 | Kotlin |
| UI 框架 | Jetpack Compose |
| 架构模式 | MVVM |
| 最低版本 | Android 7.0 (API 24) |
| 目标版本 | Android 14 (API 34) |

## 模块说明

### QuizEngine
题目生成引擎，负责：
- 生成随机减法题目（被减数 1-20，减数 0-被减数）
- 校验用户答案

### SoundManager
音效管理，支持：
- TextToSpeech 中文语音反馈（"太棒了！" / "再试试看"）
- ToneGenerator 系统音效降级（TTS 不可用时）

### QuizViewModel
状态管理核心：
- 管理题目状态、用户答案、正确计数
- 处理答题逻辑和下一题流程
- 协调 QuizEngine 和 SoundManager

## 屏幕流转

```
HomeScreen → QuizScreen → ResultScreen
    ↓           ↓             ↓
  开始练习    答题反馈      重新开始
```

## 开发构建

```bash
# 构建 Debug APK
./gradlew assembleDebug

# 构建 Release APK
./gradlew assembleRelease
```

APK 输出路径: `app/build/outputs/apk/debug/app-debug.apk`

## 版本历史

| 版本 | 日期 | 变更 |
|------|------|------|
| v1.0 | 2026-03-28 | 初始版本：减法练习功能 |
| v1.1 | 2026-03-28 | 添加 TTS 中文语音反馈 |
