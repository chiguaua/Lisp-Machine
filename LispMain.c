#include <windows.h>


//Компилировать - gcc LispMain.c -o test.exe -mwindows
//Предыдущее окно надо закрыть перед компиляцией!

const char g_szClassName[] = "myWindowClass";

// Идентификаторы элементов управления
#define ID_EDIT_IN 101
#define ID_EDIT_OUT 102
#define ID_BTN_TRANSFER 103

// Прототип функции обработчика сообщений
LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);


// VVVV Куда-то сюда надо засунуть логику функции
void TransferText(HWND hwnd) {
    int lineCount = SendMessage(GetDlgItem(hwnd, ID_EDIT_IN), EM_GETLINECOUNT, 0, 0);
    int lineLength, totalLength = 0;
    char lineBuffer[1024]; // Буфер для одной строки, предполагаем, что строки не будут длиннее
    char *fullText = NULL; // Указатель для хранения полного текста

    // Оценка общего размера текста для выделения памяти
    for (int i = 0; i < lineCount; i++) {
        lineLength = SendMessage(GetDlgItem(hwnd, ID_EDIT_IN), EM_LINELENGTH, SendMessage(GetDlgItem(hwnd, ID_EDIT_IN), EM_LINEINDEX, i, 0), 0);
        totalLength += lineLength + 2; // +2 для символов новой строки
    }

    // Выделение памяти под весь текст
    fullText = (char*)malloc(totalLength + 1); // +1 для нуль-терминатора
    if (!fullText) return; // Если память не выделена, выходим
    fullText[0] = '\0'; // Инициализация первого символа нулем

    // Чтение и копирование строк
    for (int i = 0; i < lineCount; i++) {
        ((WORD*)lineBuffer)[0] = sizeof(lineBuffer); // Первые два байта буфера должны содержать размер буфера
        lineLength = SendMessage(GetDlgItem(hwnd, ID_EDIT_IN), EM_GETLINE, i, (LPARAM)lineBuffer);
        lineBuffer[lineLength] = '\0'; // Добавляем нуль-терминатор в конец строки
        strcat(fullText, lineBuffer); // Добавляем строку к полному тексту
        strcat(fullText, "\r\n"); // Добавляем символы новой строки
    }

    // Установка текста во второе поле
    SetWindowText(GetDlgItem(hwnd, ID_EDIT_OUT), fullText);

    // Освобождение выделенной памяти
    free(fullText);
}
//~~~~Дальше начинается рисовалка~~~~~

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
    WNDCLASSEX wc;
    HWND hwnd, hEditIn, hEditOut, hButtonTransfer;
    MSG Msg;

    wc.cbSize = sizeof(WNDCLASSEX);
    wc.style = 0;
    wc.lpfnWndProc = WndProc;
    wc.cbClsExtra = 0;
    wc.cbWndExtra = 0;
    wc.hInstance = hInstance;
    wc.hIcon = LoadIcon(NULL, IDI_APPLICATION);
    wc.hCursor = LoadCursor(NULL, IDC_ARROW);
    wc.hbrBackground = (HBRUSH)(COLOR_WINDOW+1);
    wc.lpszMenuName = NULL;
    wc.lpszClassName = g_szClassName;
    wc.hIconSm = LoadIcon(NULL, IDI_APPLICATION);

    if(!RegisterClassEx(&wc)) {
        MessageBox(NULL, "Ошибка регистрации класса окна!", "Ошибка!", MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }

    hwnd = CreateWindowEx(
        WS_EX_CLIENTEDGE,
        g_szClassName,
        "Lisp to gcc",
        WS_OVERLAPPEDWINDOW,
        CW_USEDEFAULT, CW_USEDEFAULT, 1200, 900, // Увеличен размер окна
        NULL, NULL, hInstance, NULL);

    if(hwnd == NULL) {
        MessageBox(NULL, "Ошибка создания окна!", "Ошибка!", MB_ICONEXCLAMATION | MB_OK);
        return 0;
    }


        // Создаем многострочное текстовое поле для ввода
    hEditIn = CreateWindowEx(WS_EX_CLIENTEDGE, "EDIT", "", 
        WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_MULTILINE | ES_AUTOVSCROLL, 
        10, 10, 550, 700, // X, Y, Len, Hi
        hwnd, (HMENU)ID_EDIT_IN, GetModuleHandle(NULL), NULL);

    // Создаем второе текстовое поле, которое нельзя редактировать
    hEditOut = CreateWindowEx(WS_EX_CLIENTEDGE, "EDIT", "", 
        WS_CHILD | WS_VISIBLE | WS_VSCROLL | ES_MULTILINE | ES_AUTOHSCROLL | ES_READONLY, 
        600, 10, 550, 700, 
        hwnd, (HMENU)ID_EDIT_OUT, GetModuleHandle(NULL), NULL);

    // Создаем кнопку для переноса текста
    hButtonTransfer = CreateWindow("BUTTON", "convert", 
        WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON, 
        500, 750, 200, 24, 
        hwnd, (HMENU)ID_BTN_TRANSFER, (HINSTANCE)GetWindowLongPtr(hwnd, GWLP_HINSTANCE), NULL);

    ShowWindow(hwnd, nCmdShow);
    UpdateWindow(hwnd);

    while(GetMessage(&Msg, NULL, 0, 0) > 0) {
        TranslateMessage(&Msg);
        DispatchMessage(&Msg);
    }
    return Msg.wParam;
}

LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam) {
    switch(msg) {
        case WM_CLOSE:
            DestroyWindow(hwnd);
            break;
        case WM_DESTROY:
            PostQuitMessage(0);
            break;
        case WM_COMMAND:
            if(LOWORD(wParam) == ID_BTN_TRANSFER) {
                TransferText(hwnd);
            }
            break;
        default:
            return DefWindowProc(hwnd, msg, wParam, lParam);
    }
    return 0;
}
