import pandas as pd
import matplotlib.pyplot as plt

# CSV dosyasını oku
df = pd.read_csv('sort_times.csv')

# Grafik çiz
plt.figure(figsize=(10, 6))
plt.plot(df['Length'], df['ParallelTime'], label='Paralel Merge Sort', marker='o')
plt.plot(df['Length'], df['SerialTime'], label='Seri Merge Sort', marker='o')
plt.xlabel('Dizi Boyutu')
plt.ylabel('Zaman (ms)')
plt.title('Merge Sort Performans Karşılaştırması')
plt.legend()
plt.grid(True, which='both', linestyle='--', linewidth=0.5)

# Noktaların değerlerini yazdır
for i, txt in enumerate(df['ParallelTime']):
    plt.annotate(txt, (df['Length'][i], df['ParallelTime'][i]), textcoords="offset points", xytext=(0,10), ha='center')

for i, txt in enumerate(df['SerialTime']):
    plt.annotate(txt, (df['Length'][i], df['SerialTime'][i]), textcoords="offset points", xytext=(0,10), ha='center')

plt.show()



# Grafik çiz
plt.figure(figsize=(10, 6))
plt.plot(df['Length'], df['Oran'], label='serial/parallel', marker='o')
plt.xlabel('Lenght')
plt.ylabel('Ratio')
plt.title('Serial/Parallel')
plt.legend()
plt.grid(True, which='both', linestyle='--', linewidth=0.5)
for i, txt in enumerate(df['Oran']):
    formatted_txt = f"{txt:.3f}"
    plt.annotate(formatted_txt, (df['Length'][i], df['Oran'][i]), textcoords="offset points", xytext=(0,10), ha='center')

plt.show()