USE [master]
GO

CREATE DATABASE [KHANHKT]

USE [KHANHKT]
GO

CREATE TABLE [dbo].[tblOrder](
	[id] [varchar](10) NOT NULL,
	[odate] [datetime] NOT NULL,
	[total] [float] NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tblOrderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productId] [varchar](10) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[total] [float] NOT NULL,
	[orderId] [varchar](5) NOT NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tblProduct](
	[id] [varchar](10) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
	[unitPrice] [float] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tblRegistration](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[lastname] [nvarchar](50) NOT NULL,
	[isAdmin] [bit] NOT NULL,
 CONSTRAINT [PK_Resgistration] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblProduct] ([id], [name], [quantity], [unitPrice], [status]) VALUES (N'1', N'JDK', 100, 900, 1)
INSERT [dbo].[tblProduct] ([id], [name], [quantity], [unitPrice], [status]) VALUES (N'2', N'Java', 10, 100, 0)
INSERT [dbo].[tblProduct] ([id], [name], [quantity], [unitPrice], [status]) VALUES (N'3', N'NetBean', 50, 1000, 1)
INSERT [dbo].[tblProduct] ([id], [name], [quantity], [unitPrice], [status]) VALUES (N'4', N'DotNet', 10, 50, 0)
INSERT [dbo].[tblProduct] ([id], [name], [quantity], [unitPrice], [status]) VALUES (N'5', N'Database', 10, 55, 1)
GO
INSERT [dbo].[tblRegistration] ([username], [password], [lastname], [isAdmin]) VALUES (N'admin', N'12345', N'Real Admin', 1)
INSERT [dbo].[tblRegistration] ([username], [password], [lastname], [isAdmin]) VALUES (N'hutruc', N'12345', N'Hu Truc', 0)
INSERT [dbo].[tblRegistration] ([username], [password], [lastname], [isAdmin]) VALUES (N'khanh@Spring', N'12345', N'Spring annotation', 0)
GO
