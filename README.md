# gdx-pack-tools

一个用于 LibGDX 项目的自动化图片打包工具，封装了打包相关方法，可以方便地将原始图片资源打包为优化后的图集。

## 使用

1. 克隆本仓库到您的本地环境
2. 将 `tools` 文件夹移动到您的项目根目录
3. 确保您的项目根目录包含以下文件夹：
   - `assets` - 用于存储原始图片资源
   - `assets-pack` - 用于存储打包后的图片资源
   - （如果不存在这些文件夹，请先创建它们，或者修改模块构建脚本中的目录参数）

## 配置项目

在您的 `settings.gradle` 文件中添加以下内容：

```gradle
include "tools"
```

完成后同步您的项目。

## 使用方法

运行 Gradle 任务：

```
./gradlew tools:assetsTool
```

或者如果您使用 Windows 系统：

```
gradlew.bat tools:assetsTool
```

## 目录结构

```
您的项目根目录/
├── assets/          # 原始图片资源（输入目录）
├── assets-pack/     # 打包后的图片资源（输出目录）
├── tools/           # 本打包工具模块
└── 其他项目文件...
```
