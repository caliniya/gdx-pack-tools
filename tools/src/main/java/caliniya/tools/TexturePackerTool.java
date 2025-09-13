package caliniya.tools;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

import java.io.File;

public class TexturePackerTool {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: TexturePackerTool <inputDir> <outputDir> <packName> [settingsFile]");
            System.out.println("Example: TexturePackerTool ./input ./output game_textures");
            System.exit(1);
        }

        File inputDir = new File(args[0]);
        File outputDir = new File(args[1]);
        String packName = args[2];
        
        // 验证输入目录
        if (!inputDir.exists() || !inputDir.isDirectory()) {
            System.err.println("Error: Input directory does not exist or is not a directory");
            System.exit(1);
        }
        
        // 清理旧的打包产物
        cleanPreviousOutput(outputDir, packName);
        
        // 创建设置
        Settings settings = new Settings();
        
        // 默认设置
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        settings.pot = true;
        settings.paddingX = 2;
        settings.paddingY = 2;
        settings.edgePadding = true;
        settings.duplicatePadding = false;
        settings.stripWhitespaceX = true;
        settings.stripWhitespaceY = true;
        settings.rotation = false;
        settings.alias = true;
        
        // 确保输出目录存在
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            System.err.println("Error: Failed to create output directory");
            System.exit(1);
        }
        
        // 执行打包
        try {
            System.out.println("Starting texture packing...");
            System.out.println("Input: " + inputDir.getAbsolutePath());
            System.out.println("Output: " + outputDir.getAbsolutePath());
            
            TexturePacker.process(settings, 
                inputDir.getAbsolutePath(), 
                outputDir.getAbsolutePath(), 
                packName);
            
            System.out.println("Texture packing completed successfully!");
            System.out.println("Output files:");
            System.out.println("- " + new File(outputDir, packName + ".png").getAbsolutePath());
            System.out.println("- " + new File(outputDir, packName + ".atlas").getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error during texture packing: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * 清理之前打包生成的文件
     */
    private static void cleanPreviousOutput(File outputDir, String packName) {
        if (!outputDir.exists()) {
            return;
        }
        
        System.out.println("Cleaning previous output files...");
        
        // 删除主要的打包文件
        deleteFile(new File(outputDir, packName + ".png"));
        deleteFile(new File(outputDir, packName + ".atlas"));
        
        // 删除可能的分页文件（texture1.png, texture2.png等）
        File[] files = outputDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                // 删除以packName开头后面跟着数字的文件
                if (fileName.startsWith(packName) && 
                    (fileName.endsWith(".png") || fileName.endsWith(".atlas"))) {
                    deleteFile(file);
                }
            }
        }
    }
    
    /**
     * 安全删除文件
     */
    private static void deleteFile(File file) {
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Deleted: " + file.getAbsolutePath());
            } else {
                System.err.println("Failed to delete: " + file.getAbsolutePath());
            }
        }
    }
}