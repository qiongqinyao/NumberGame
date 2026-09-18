
#这是一个脚本来批量创建10个数字动画xml文件


#每个数字动画共有24帧
FrameCount = 24
OUT_DIR = r"E:\Administrator\AndroidAPP\NumberGame\app\src\main\res\drawable"

for i in range(1,10) :
    lines = []
    lines.append('<?xml version="1.0" encoding="utf-8"?>')
    lines.append('<animation-list xmlns:android="http://schemas.android.com/apk/res/android"')
    lines.append('    android:oneshot="true">')

    for j in range(1 , FrameCount + 1):
        lines.append(f'    <item android:drawable="@drawable/on{i}_{j}" android:duration="100" />')

    lines.append('</animation-list>')
    path = f"{OUT_DIR}\\anim_on{i}.xml"
    with open(path, "w", encoding="utf-8") as f:
        f.write('\n'.join(lines))

    print(f"已生成: {path}")